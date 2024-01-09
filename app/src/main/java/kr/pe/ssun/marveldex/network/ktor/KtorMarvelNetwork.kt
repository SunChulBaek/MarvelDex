package kr.pe.ssun.marveldex.network.ktor

import kr.pe.ssun.marveldex.network.MarvelNetworkDataSource
import kr.pe.ssun.marveldex.network.model.NetworkCharacter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import kr.pe.ssun.marveldex.BuildConfig
import kr.pe.ssun.marveldex.network.model.NetworkWrapper
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest
import java.security.cert.X509Certificate
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Singleton
class KtorMarvelNetwork @Inject constructor() : MarvelNetworkDataSource {

    private val client = HttpClient(CIO) {
        // TODO : remove this
        engine {
            https {
                trustManager = object: X509TrustManager {
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) { }

                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) { }

                    override fun getAcceptedIssuers(): Array<X509Certificate>? = null
                }
            }
        }
        install(ContentNegotiation) {
            gson()
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.d("ktor : $message")
                }
            }
            level = LogLevel.ALL
        }

        defaultRequest {
            url("https://gateway.marvel.com:443/")
        }
    }

    override suspend fun getCharacters(): NetworkWrapper<NetworkCharacter> = client.get("v1/public/characters") {
        url {
            val ts = (System.currentTimeMillis() / 1000).toString()
            val publicKey = BuildConfig.MARVEL_PUBLIC_KEY
            val privateKey = BuildConfig.MARVEL_PRIVATE_KEY
            val md5 = md5("$ts$privateKey$publicKey")
            parameters.append("ts", ts)
            parameters.append("apikey", publicKey)
            parameters.append("hash", md5)
        }
    }.body()

    private fun md5(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(str.toByteArray())).toString(16).padStart(32, '0')
    }
}