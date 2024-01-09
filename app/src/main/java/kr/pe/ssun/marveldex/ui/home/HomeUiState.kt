package kr.pe.ssun.marveldex.ui.home

import kr.pe.ssun.marveldex.data.model.Character

sealed interface HomeUiState {
    data class Success(
        val photos: List<Character> = listOf()
    ) : HomeUiState
    object Loading : HomeUiState
    object Error: HomeUiState
}