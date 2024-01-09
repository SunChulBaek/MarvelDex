package kr.pe.ssun.marveldex.data.repository

import kr.pe.ssun.marveldex.data.model.Character
import kr.pe.ssun.marveldex.network.SsunNetworkDataSource
import kr.pe.ssun.marveldex.network.model.NetworkCharacter
import kr.pe.ssun.marveldex.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.pe.ssun.marveldex.network.model.NetworkContainer
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeRepository @Inject constructor(
    private val network: SsunNetworkDataSource
) {

    fun getCharacters(): Flow<List<Character>?> = flow {
        val data: NetworkContainer<NetworkCharacter>? = network.getCharacters().data
        emit(data?.results?.map(NetworkCharacter::asExternalModel))
    }
}