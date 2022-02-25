package com.diwixis.filmlibrary.presentation

import androidx.compose.runtime.Composable
import com.diwixis.filmlibrary.domain.MoviesRepository
import org.kodein.di.compose.androidContextDI
import org.kodein.di.instance

@Composable
fun MoviesGreedScreen() {
    val di = androidContextDI()
    val repository: MoviesRepository by di.instance()

//    LazyVerticalGrid(
//        cells = GridCells.Fixed(3),
//        contentPadding = PaddingValues(8.dp)
//    ) {
//        items(data) { item ->
//
//        }
//    }
}