package kr.pe.ssun.marveldex.di

import kr.pe.ssun.marveldex.network.ktor.KtorMarvelNetwork
import kr.pe.ssun.marveldex.data.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesFakeRepository(
        apiService: KtorMarvelNetwork
    ): MarvelRepository {
        return MarvelRepository(apiService)
    }
}