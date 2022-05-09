package com.diwixis.filmlibrary.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.presentation.state.MovieListState

@Composable
fun MoviesRow(state: MovieListState, onShowAll: () -> Unit, title: String = "") {
    Column {
        if (title.isNotEmpty()) {
            Box {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
        when (state) {
            is MovieListState.Loading -> {
                Text("Loading process")
            }
            is MovieListState.Error -> {
                Text(state.message)
            }
            is MovieListState.Data -> {
                LazyRow(verticalAlignment = Alignment.CenterVertically) {
                    items(state.data) {
                        MovieItem(it)
                    }
                    item {
                        ShowMoreItem(onClick = onShowAll)
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieItem(movie: Movie) {
    Column(
        modifier = Modifier
            .width(125.dp)
            .padding(start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PosterView(movie.posterPath, 16.dp)
        (movie.title ?: movie.originalTitle)?.also {
            Text(
                it,
                style = TextStyle(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        movie.releaseDate?.also {
            Text(
                it,
                style = TextStyle(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}