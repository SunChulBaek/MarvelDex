package kr.pe.ssun.marveldex.domain

import kr.pe.ssun.marveldex.data.repository.MarvelRepository
import kr.pe.ssun.marveldex.data.model.Character
import kr.pe.ssun.marveldex.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MarvelRepository
) : FlowUseCase<Any?, List<Character>>(dispatcher) {

    override fun execute(parameters: Any?): Flow<Result<List<Character>>> =
        repository.getCharacters().map { characters ->
            characters?.let {
                Result.success(characters)
            } ?: run {
                Result.failure(Throwable("Error"))
            }
        }
}