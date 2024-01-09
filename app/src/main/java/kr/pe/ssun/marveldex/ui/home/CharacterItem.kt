package kr.pe.ssun.marveldex.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import kr.pe.ssun.marveldex.data.model.Character
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoItem(
    modifier: Modifier = Modifier,
    item: Character,
    onClick: () -> Unit
) {
    Timber.i("[sunchulbaek] url = ${item.thumbnail}")
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(80.dp),
                model = item.thumbnail,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxSize(),
                text = item.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}