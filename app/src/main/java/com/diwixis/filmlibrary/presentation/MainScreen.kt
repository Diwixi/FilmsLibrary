package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diwixis.filmlibrary.domain.viewmodels.MainViewModel
import com.diwixis.filmlibrary.navigation.GreedType
import com.diwixis.filmlibrary.presentation.components.MoviesBigRow
import com.diwixis.filmlibrary.presentation.components.MoviesRow
import org.koin.androidx.compose.viewModel

@Composable
fun MoviesMainScreen(
    onOpenGreedScreen: (type: GreedType) -> Unit,
    onOpenDetailsScreen: (id: Int) -> Unit,
) {
    val viewModel: MainViewModel by viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchTopMovies()
        viewModel.fetchPopMovies()
        viewModel.fetchNowPlayingMovies()
    }

    val stateTop by viewModel.topState.collectAsState()
    val statePop by viewModel.popState.collectAsState()
    val stateNowPlaying by viewModel.nowPlayingState.collectAsState()

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
                    onOpenGreedScreen(GreedType.NOW)
                }
            )
        }
        item {
            MoviesRow(state = statePop, title = "What's Popular", onShowAll = {
                onOpenGreedScreen(GreedType.POP)
            })
        }
        item {
            MoviesRow(state = stateTop, title = "Top movies", onShowAll = {
                onOpenGreedScreen(GreedType.TOP)
            })
        }
    }
}