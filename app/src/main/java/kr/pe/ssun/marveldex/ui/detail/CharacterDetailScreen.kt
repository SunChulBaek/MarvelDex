package kr.pe.ssun.marveldex.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    name: String?,
    thumbnail: String?
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val character = (uiState as? CharacterDetailUiState.Success)?.character

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(name ?: "") }) },
        snackbarHost = { SnackbarHost(snackbarHostState)}
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f),
                model = thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            if (uiState == CharacterDetailUiState.Loading) {
                CircularProgressIndicator()
            } else if (uiState == CharacterDetailUiState.Error) {
                Text(text = "Error", style = TextStyle(color = Color.Red))
            }
        }
    }
}