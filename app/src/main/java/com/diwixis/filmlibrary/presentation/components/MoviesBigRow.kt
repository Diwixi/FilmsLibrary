package com.diwixis.filmlibrary.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.presentation.MovieListState
import com.diwixis.filmlibrary.presentation.UiState

@Composable
fun MoviesBigRow(
    state: MovieListState,
    onItemClick: (Int) -> Unit,
    onShowAllClick: () -> Unit,
    title: String = ""
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
            is UiState.Data -> {
                LazyRow(verticalAlignment = Alignment.CenterVertically) {
                    items(state.data) {
                        MovieBigItem(it, onItemClick)
                    }
                    item {
                        ShowMoreItem(onClick = onShowAllClick)
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieBigItem(movie: Movie, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
            .padding(start = 16.dp)
            .clickable { onClick(movie.id) },
        contentAlignment = Alignment.BottomCenter
    ) {
        PosterView(movie.posterPath, 8.dp)
        (movie.title ?: movie.originalTitle)?.also {
            Text(
                it,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}