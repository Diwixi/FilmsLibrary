package com.pg.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.pg.movies.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect

@Composable
fun MovieListScreen() {
    val viewmodel = MoviesViewModel()
    var state by remember { mutableStateOf(MovieListState()) }

    LaunchedEffect(viewmodel.state) {
        viewmodel.state.collect { vmState ->
            state = vmState
        }
    }

    when {
        state.loading -> {
            //Loading
        }
        state.networkError -> {
            //Error
        }
        else -> {
            LazyColumn {
                items(state.movies) {
                    MovieItem(it)
                }
            }
        }
    }
}

data class MovieListState(
    val movies: List<Movie> = listOf(Movie(0, "one"), Movie(1, "two"), Movie(2, "three")),
    val loading: Boolean = false,
    val networkError: Boolean = false,
)

class MoviesViewModel : ViewModel() {
    private val _state: MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()
}