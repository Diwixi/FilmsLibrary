package com.diwixis.filmlibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import com.diwixis.filmlibrary.domain.Movie

@Composable
fun MovieItem(movie: Movie) {
    Column(
        modifier = Modifier
            .width(125.dp)
            .padding(start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillWidth,
            )
        }
        (movie.title ?: movie.originalTitle)?.also {
            Text(it, style = TextStyle(fontSize = 12.sp), maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        movie.releaseDate?.also {
            Text(it, style = TextStyle(fontSize = 12.sp), maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}