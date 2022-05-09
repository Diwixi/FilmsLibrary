package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.navigation.GreedType
import com.diwixis.filmlibrary.presentation.components.PosterView
import org.koin.androidx.compose.viewModel

@Composable
fun MovieListScreen(type: GreedType?) {

//    val viewModel: MovieListViewModel by viewModel()

//    LaunchedEffect(Unit) {
//        viewModel.fetchMoviesByType(type)
//    }

//    val state by viewModel.state.collectAsState()

//    when {
//        state.loading -> {
//                 TODO: Shimmer
//            Text("Loading")
//        }
//        state.isError -> {
//                 TODO: Show error view
//            Text("Error")
//        }
//        else -> {
//            LazyColumn {
//                items(state.movies) { item ->
//                    MovieItem(movie = item)
//                }
//            }
//        }
//    }
}

@Composable
private fun Shimmer() {
    Text("Shimmer")
}

@Composable
private fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PosterView(posterPath = movie.posterPath, cornerSize = 8.dp)
        movie.title?.let { Text(text = it, modifier = Modifier.weight(1f)) }
    }
}