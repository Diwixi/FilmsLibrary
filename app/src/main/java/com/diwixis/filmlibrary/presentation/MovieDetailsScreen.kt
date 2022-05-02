package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diwixis.filmlibrary.domain.MoviesRepository
import org.kodein.di.compose.androidContextDI
import org.kodein.di.instance

@Composable
fun MovieDetailsScreen(movieId: Int?) {
    val di = androidContextDI()
    val repository: MoviesRepository by di.instance()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("MovieDetailcreen")
    }
}