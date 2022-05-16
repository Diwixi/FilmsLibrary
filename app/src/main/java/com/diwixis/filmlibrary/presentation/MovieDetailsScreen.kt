package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.viewmodels.MovieDetailsViewModel
import com.diwixis.filmlibrary.presentation.components.PosterView
import com.diwixis.filmlibrary.presentation.components.Spacing
import org.koin.androidx.compose.viewModel

@Composable
fun MovieDetailsScreen(movieId: Int?) {

    val viewModel: MovieDetailsViewModel by viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchMovie(movieId)
    }

    val _state: MovieState by viewModel.state.collectAsState()

    when (val state = _state) { //-> because collectAsState is delegate
        is UiState.Loading -> Shimmer()
        is UiState.Error -> {
            // TODO: Show error view
            Text(state.message)
        }
        is UiState.Data -> {
            Content(state.data)
        }
    }
}

@Composable
private fun Shimmer() {
    // TODO: Add Shimmer
    Text(text = "Shimmer")
}

@Composable
private fun Content(movie: Movie) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        PosterView(
            posterPath = movie.posterPath,
            modifier = Modifier.height(300.dp).fillMaxWidth()
        )
        Spacing(size = 8.dp)
        Text(movie.name)
        Spacing(size = 8.dp)
        movie.popularity?.let { Text(it.toString()) }
        movie.releaseDate?.let { Text(it) }
        Spacing(size = 8.dp)
        Text("Overview")
        Spacing(size = 8.dp)
        movie.overview?.let { Text(it) }
    }
}