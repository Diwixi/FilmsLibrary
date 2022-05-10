package com.diwixis.filmlibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale

@Composable
fun PosterView(posterPath: String?, cornerSize: Dp, modifier: Modifier = Modifier.fillMaxSize()) {
    if (posterPath == null) {
        Shimmer()
    } else {
        Image(
            painter = rememberImagePainter(
                data = posterPath,
                builder = {
                    size(OriginalSize)
                    scale(Scale.FIT)
                }
            ),
            contentDescription = null,
            modifier = modifier.clip(RoundedCornerShape(cornerSize)),
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
private fun Shimmer() {
    // TODO: Shimmer
    Text("Shimmer")
}