package kr.pe.ssun.marveldex.network

import kr.pe.ssun.marveldex.network.model.NetworkCharacter
import kr.pe.ssun.marveldex.network.model.NetworkWrapper

interface MarvelNetworkDataSource {
    suspend fun getCharacters(
        limit: Int?,
        offset: Int?
    ): NetworkWrapper<NetworkCharacter>
}