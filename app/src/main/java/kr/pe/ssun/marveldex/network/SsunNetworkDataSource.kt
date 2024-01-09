package kr.pe.ssun.marveldex.network

import kr.pe.ssun.marveldex.network.model.NetworkCharacter
import kr.pe.ssun.marveldex.network.model.NetworkWrapper

interface SsunNetworkDataSource {
    suspend fun getCharacters(): NetworkWrapper<NetworkCharacter>
}