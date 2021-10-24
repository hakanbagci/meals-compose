package dev.hakanbagci.search.ui

import androidx.compose.runtime.Immutable
import dev.hakanbagci.search.domain.entity.Meal
import dev.hakanbagci.search.domain.entity.SearchMealsResult

@Immutable
data class SearchUiState(
    val query: String = "",
    val result: SearchResultUiState = SearchResultUiState.Initial
)

sealed class SearchResultUiState {
    object Initial : SearchResultUiState()
    object Error : SearchResultUiState()
    object NoResults : SearchResultUiState()
    data class Success(val meals: List<Meal>) : SearchResultUiState()
}

fun SearchMealsResult.toResultUiState(): SearchResultUiState {
    return when (this) {
        SearchMealsResult.Error -> SearchResultUiState.Error
        SearchMealsResult.NoResults -> SearchResultUiState.NoResults
        is SearchMealsResult.Success -> SearchResultUiState.Success(this.meals)
    }
}
