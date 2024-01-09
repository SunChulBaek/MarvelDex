package kr.pe.ssun.marveldex.network.di

import kr.pe.ssun.marveldex.network.MarvelNetworkDataSource
import kr.pe.ssun.marveldex.network.ktor.KtorMarvelNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindKtorMarvelNetwork(
        network: KtorMarvelNetwork
    ): MarvelNetworkDataSource
}