package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diwixis.filmlibrary.domain.MoviesRepository
import com.diwixis.filmlibrary.navigation.GreedType
import org.kodein.di.compose.androidContextDI
import org.kodein.di.instance

@Composable
fun MoviesGreedScreen(find: GreedType?) {
    val di = androidContextDI()
    val repository: MoviesRepository by di.instance()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("MoviesGreedScreen")
    }

//    LazyVerticalGrid(
//        cells = GridCells.Fixed(3),
//        contentPadding = PaddingValues(8.dp)
//    ) {
//        items(data) { item ->
//
//        }
//    }
}