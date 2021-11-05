package dev.hakanbagci.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dev.hakanbagci.search.domain.entity.Meal
import dev.hakanbagci.search.ui.SearchResultUiState.*

@ExperimentalCoilApi
@Composable
fun SearchResult(state: SearchResultUiState) {
    when (state) {
        Initial -> ResultFallback(message = "Please query meals from search bar")
        NoResults -> ResultFallback(message = "No results")
        Error -> ResultFallback(message = "Error")
        is Success -> MealItems(meals = state.meals)
    }
}

@ExperimentalCoilApi
@Composable
private fun MealItems(
    meals: List<Meal>
) {
    LazyColumn {
        items(meals) { meal ->
            MealItem(meal = meal, onMealClick = {})
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun MealItem(
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
                .align(CenterVertically)
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
                .align(CenterVertically)
                .padding(8.dp)
        )
    }
}

@Composable
private fun ResultFallback(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = modifier.align(Center),
            text = message
        )
    }
}
