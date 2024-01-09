package kr.pe.ssun.marveldex.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kr.pe.ssun.marveldex.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val uiState = getCharactersUseCase(null)
        .map { result ->
            result.getOrNull()?.let { characters ->
                HomeUiState.Success(characters)
            } ?: HomeUiState.Error
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )
}