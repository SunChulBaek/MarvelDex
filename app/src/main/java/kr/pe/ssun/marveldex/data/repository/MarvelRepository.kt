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

    fun getCharacters(
        limit: Int?,
        offset: Int?
    ): Flow<List<Character>?> = flow {
        val data: NetworkContainer<NetworkCharacter>? = network.getCharacters(limit = limit, offset = offset).data
        emit(data?.results?.map(NetworkCharacter::asExternalModel))
    }

    fun getCharacter(
        id: Int
    ): Flow<Character?> = flow {
        val data = network.getCharacter(id = id).data
        emit(data?.results?.get(0)?.asExternalModel())
    }
}