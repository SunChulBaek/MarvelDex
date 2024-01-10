package kr.pe.ssun.marveldex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import kr.pe.ssun.marveldex.ui.common.ErrorScreen
import kr.pe.ssun.marveldex.ui.common.LoadingScreen

@Composable
fun HomeContent(
    viewModel: HomeViewModel = hiltViewModel(),
    navigate: (String, Any?) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val characters = viewModel.characters.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (uiState) {
            HomeUiState.Loading -> LoadingScreen()
            HomeUiState.Error -> ErrorScreen()
            is HomeUiState.Success -> {
                val photos = (uiState as HomeUiState.Success).photos
                Box(modifier = Modifier) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = listState
                    ) {
                        items(
                            count = characters.itemCount,
                            itemContent = { index ->
                                PhotoItem(modifier = Modifier.padding(top = 10.dp), item = characters[index]!!) {
                                    navigate("character_detail", Pair("aaa", "bbb"))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}