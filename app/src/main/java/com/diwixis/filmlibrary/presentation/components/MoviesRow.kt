package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diwixis.filmlibrary.presentation.components.MovieItem
import com.diwixis.filmlibrary.presentation.components.ShowMoreItem
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
        when {
            state.loading -> {
                Text("Loading process")
            }
            state.error != null -> {
                Text(state.error)
            }
            else -> {
                LazyRow(verticalAlignment = Alignment.CenterVertically) {
                    items(state.movies) {
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