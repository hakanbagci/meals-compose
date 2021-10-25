package dev.hakanbagci.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import dev.hakanbagci.search.domain.entity.Meal
import dev.hakanbagci.search.ui.SearchResultUiState.*

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
            when (state.result) {
                Initial -> {
                    Box(
                        modifier = modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = modifier.align(Alignment.Center),
                            text = "Please query meals from search bar"
                        )
                    }
                }
                NoResults -> {
                    Box(
                        modifier = modifier.fillMaxSize(),
                    ) {
                        Text(
                            modifier = modifier.align(Alignment.Center),
                            text = "No Results"
                        )
                    }
                }
                Error -> {
                    Box(
                        modifier = modifier.fillMaxSize(),
                    ) {
                        Text(
                            modifier = modifier.align(Alignment.Center),
                            text = "Error"
                        )
                    }
                }
                is Success -> {
                    LazyColumn {
                        items(state.result.meals) { meal ->
                            SearchResult(meal = meal, onMealClick = {})
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun SearchResult(
    meal: Meal,
    onMealClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onMealClick(meal.id) }
            .padding(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(percent = 16),
            modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = meal.thumbnailUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = meal.name,
                modifier = modifier
                    .width(72.dp)
                    .height(72.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            text = meal.name,
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
        )
    }
}
