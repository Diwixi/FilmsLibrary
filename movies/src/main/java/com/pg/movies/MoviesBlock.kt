package com.pg.movies

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pg.movies.domain.Movie

@Composable
fun MoviesBlock(state: MovieBlockState) {
    if (state.loading) {
        CircularProgressIndicator()
    } else {
        Text("Title - $state.title")
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow {
            items(items = state.movies) { item ->
                MovieItem(item)
            }
        }
    }
}

data class MovieBlockState(
    val title: String,
    val movies: List<Movie>,
    val loading: Boolean,
    val networkError: Boolean,
)