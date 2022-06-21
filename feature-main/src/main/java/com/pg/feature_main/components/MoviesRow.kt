package com.pg.feature_main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diwixis.filmlibrary.domain.Action
import com.diwixis.filmlibrary.domain.DataAction
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.presentation.MovieListState
import com.pg.base_ui.UiState
import com.pg.base_ui.PosterView

@Composable
fun MoviesRow(
    state: MovieListState,
    onShowAll: Action,
    title: String = "",
    onItemClick: DataAction<Int>
) {
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
            is UiState.Loading -> {
                Text("Loading process")
            }
            is UiState.Error -> {
                Text(state.message)
            }
            is UiState.Data<Movie> -> {
                LazyRow(verticalAlignment = Alignment.CenterVertically) {
                    items(state.data) {
                        MovieItem(it, onItemClick)
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
private fun MovieItem(movie: Movie, onClick: DataAction<Int>) {
    Column(
        modifier = Modifier
            .width(125.dp)
            .padding(start = 16.dp)
            .clickable { onClick(movie.id) },
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