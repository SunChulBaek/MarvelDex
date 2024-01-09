package kr.pe.ssun.marveldex.di

import kr.pe.ssun.marveldex.network.ktor.KtorSsunNetwork
import kr.pe.ssun.marveldex.data.repository.FakeRepository
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
        apiService: KtorSsunNetwork
    ): FakeRepository {
        return FakeRepository(apiService)
    }
}