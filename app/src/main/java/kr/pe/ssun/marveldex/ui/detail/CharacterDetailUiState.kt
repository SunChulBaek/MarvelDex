package kr.pe.ssun.marveldex.ui.detail

import kr.pe.ssun.marveldex.data.model.Character

sealed interface CharacterDetailUiState {
    data class Success(
        val character: Character
    ) : CharacterDetailUiState
    object Loading : CharacterDetailUiState
    object Error : CharacterDetailUiState
}