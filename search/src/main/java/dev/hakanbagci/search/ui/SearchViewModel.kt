package dev.hakanbagci.search.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hakanbagci.search.domain.usecase.SearchMeals
import dev.hakanbagci.search.ui.SearchResultUiState.Initial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_DEBOUNCE_INTERVAL_MS = 300L

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMeals: SearchMeals
) : ViewModel() {

    var uiState by mutableStateOf(SearchUiState())
        private set

    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(query = query)
            delay(SEARCH_DEBOUNCE_INTERVAL_MS)
            val result = if (query.isBlank())
                Initial
            else
                searchMeals(query).toResultUiState()
            uiState = uiState.copy(result = result)
        }
    }
}
