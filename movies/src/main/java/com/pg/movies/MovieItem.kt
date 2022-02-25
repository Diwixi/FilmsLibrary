package com.pg.movies

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.pg.movies.domain.Movie

@Composable
fun MovieItem(movie: Movie) {
    Row {
        //Image
        Text(movie.title ?: movie.originalTitle ?: "Unknown")
    }
}