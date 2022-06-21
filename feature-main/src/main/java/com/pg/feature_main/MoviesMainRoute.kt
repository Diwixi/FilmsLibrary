package com.pg.feature_main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pg.feature_main.components.MoviesBigRow
import com.pg.feature_main.components.MoviesRow
import com.diwixis.filmlibrary.navigation.ListType
import com.diwixis.filmlibrary.presentation.MovieListState
import org.koin.androidx.compose.viewModel

@Composable
fun MoviesMainRoute(
    onBackClick: () -> Unit, //сделать onBack по новому
    onOpenListScreen: (type: ListType) -> Unit,
    onOpenDetailsScreen: (id: Int) -> Unit,
) {
    val viewModel: MoviesMainViewModel by viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchTopMovies()
        viewModel.fetchPopMovies()
        viewModel.fetchNowPlayingMovies()
    }

    val stateTop by viewModel.topState.collectAsState()
    val statePop by viewModel.popState.collectAsState()
    val stateNowPlaying by viewModel.nowPlayingState.collectAsState()

    MoviesMainScreen(
        stateTop,
        statePop,
        stateNowPlaying,
        onOpenListScreen,
        onOpenDetailsScreen
    )
}

@Composable
fun MoviesMainScreen(
    stateTop: MovieListState,
    statePop: MovieListState,
    stateNowPlaying: MovieListState,
    onOpenGreedScreen: (type: ListType) -> Unit,
    onOpenDetailsScreen: (id: Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            MoviesBigRow(
                state = stateNowPlaying,
                title = "Movies now playing",
                onItemClick = onOpenDetailsScreen,
                onShowAllClick = {
                    onOpenGreedScreen(ListType.NOW)
                }
            )
        }
        item {
            MoviesRow(
                state = statePop,
                title = "What's Popular",
                onShowAll = {
                    onOpenGreedScreen(ListType.POP)
                },
                onItemClick = { movieId ->
                    onOpenDetailsScreen(movieId)
                }
            )
        }
        item {
            MoviesRow(
                state = stateTop,
                title = "Top movies",
                onShowAll = {
                    onOpenGreedScreen(ListType.TOP)
                },
                onItemClick = { movieId ->
                    onOpenDetailsScreen(movieId)
                }
            )
        }
    }
}