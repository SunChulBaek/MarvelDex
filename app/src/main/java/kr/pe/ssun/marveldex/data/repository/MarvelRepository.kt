package kr.pe.ssun.marveldex.data.repository

import kr.pe.ssun.marveldex.data.model.Character
import kr.pe.ssun.marveldex.network.MarvelNetworkDataSource
import kr.pe.ssun.marveldex.network.model.NetworkCharacter
import kr.pe.ssun.marveldex.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.pe.ssun.marveldex.network.model.NetworkContainer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val network: MarvelNetworkDataSource
) {

    fun getCharacters(): Flow<List<Character>?> = flow {
        val data: NetworkContainer<NetworkCharacter>? = network.getCharacters().data
        emit(data?.results?.map(NetworkCharacter::asExternalModel))
    }
}