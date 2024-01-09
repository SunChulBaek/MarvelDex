package kr.pe.ssun.marveldex.network.di

import kr.pe.ssun.marveldex.network.SsunNetworkDataSource
import kr.pe.ssun.marveldex.network.ktor.KtorSsunNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SsunNetworkModule {
    @Binds
    fun bindKtorSsunNetwork(
        network: KtorSsunNetwork
    ): SsunNetworkDataSource
}