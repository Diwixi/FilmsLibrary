package com.diwixis.filmlibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.presentation.state.MovieListState

@Composable
fun MoviesBigRow(state: MovieListState, onShowAll: () -> Unit, title: String = "") {
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
                        MovieBigItem(it)
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
private fun MovieBigItem(movie: Movie) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
            .padding(start = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        movie.posterPath?.also {
            Image(
                painter = rememberImagePainter(
                    data = it,
                    builder = {
                        size(OriginalSize)
                        scale(Scale.FIT)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth,
            )
        }
        (movie.title ?: movie.originalTitle)?.also {
            Text(
                it,
                style = TextStyle(fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}