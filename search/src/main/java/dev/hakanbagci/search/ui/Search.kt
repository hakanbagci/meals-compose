package dev.hakanbagci.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.statusBarsPadding

@ExperimentalCoilApi
@Composable
fun Search(
    state: SearchUiState,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = modifier.statusBarsPadding())
            SearchBar(
                query = state.query,
                onQueryChange = onQueryChange
            )
            Divider()
            SearchResult(state = state.result)
        }
    }
}

