package kr.pe.ssun.marveldex.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.pe.ssun.marveldex.domain.GetCharacterParam
import kr.pe.ssun.marveldex.domain.GetCharacterUseCase
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    val id = savedStateHandle.get<Int>("id") ?: 0

    val uiState = getCharacterUseCase(GetCharacterParam(id))
        .map { result ->
            result.getOrNull()?.let {
                CharacterDetailUiState.Success(it)
            } ?: CharacterDetailUiState.Error
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterDetailUiState.Loading
        )
}