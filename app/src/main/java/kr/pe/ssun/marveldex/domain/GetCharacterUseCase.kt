package kr.pe.ssun.marveldex.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.pe.ssun.marveldex.data.model.Character
import kr.pe.ssun.marveldex.data.repository.MarvelRepository
import kr.pe.ssun.marveldex.util.IoDispatcher
import javax.inject.Inject

data class GetCharacterParam(
    val id: Int
)

class GetCharacterUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MarvelRepository
) : FlowUseCase<GetCharacterParam, Character>(dispatcher) {

    override fun execute(parameters: GetCharacterParam): Flow<Result<Character>> =
        repository.getCharacter(parameters.id).map { character ->
            character?.let {
                Result.success(character)
            } ?: run {
                Result.failure(Throwable("Error"))
            }
        }
}