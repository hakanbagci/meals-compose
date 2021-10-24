package dev.hakanbagci.meals.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.hakanbagci.meals.ui.theme.MealsTheme
import dev.hakanbagci.search.ui.Search
import dev.hakanbagci.search.ui.SearchViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Search(
                        state = searchViewModel.uiState,
                        onQueryChange = { searchViewModel.search(it) }
                    )
                }
            }
        }
    }
}
