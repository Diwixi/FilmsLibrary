package com.diwixis.filmlibrary.features.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.navigation.ListType
import com.diwixis.filmlibrary.presentation.MovieListState
import com.diwixis.filmlibrary.presentation.UiState
import com.diwixis.filmlibrary.presentation.components.PosterView
import org.koin.androidx.compose.viewModel

@Composable
fun MovieListRoute(
    onBackClick: () -> Unit,
    type: ListType?
) {
    val viewModel: MovieListViewModel by viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchMovies(type)
    }

    val _state: MovieListState by viewModel.state.collectAsState()

    when (val state = _state) { //-> because collectAsState is delegate
        is UiState.Loading -> Shimmer()
        is UiState.Error -> {
            // TODO: Show error view
            Text(state.message)
        }
        is UiState.Data -> {
            MovieListScreen(movieList = state.data)
        }
    }
}

@Composable
fun MovieListScreen(movieList: List<Movie>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(movieList) { item ->
            MovieItem(movie = item)
        }
    }
}

@Composable
private fun Shimmer() {
    Text("Shimmer")
}

@Composable
private fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PosterView(
            posterPath = movie.posterPath,
            cornerSize = 8.dp,
            modifier = Modifier
                .fillMaxHeight()
                .width(150.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MovieInfo(movie)
        }
    }
}

@Composable
private fun MovieInfo(movie: Movie) {
    Text(text = movie.name)
    movie.releaseDate?.let { Text(text = it) }
    movie.popularity?.let { Text(text = it.toString()) }
}