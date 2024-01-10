package kr.pe.ssun.marveldex.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kr.pe.ssun.marveldex.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.pe.ssun.marveldex.data.CharacterPagingSource
import kr.pe.ssun.marveldex.domain.GetCharactersParam
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pagingSource: CharacterPagingSource,
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val characters = Pager(
        config = PagingConfig(pageSize = 20)
    ) { pagingSource }.flow.cachedIn(viewModelScope)

    val uiState = getCharactersUseCase(GetCharactersParam(limit = 20, offset = 0))
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