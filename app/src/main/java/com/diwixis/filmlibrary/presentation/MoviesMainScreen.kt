package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.domain.MoviesRepository
import com.diwixis.filmlibrary.navigation.GreedType
import com.diwixis.filmlibrary.presentation.components.MoviesBigRow
import com.diwixis.filmlibrary.presentation.state.stateShortProducer
import org.kodein.di.compose.androidContextDI
import org.kodein.di.instance

@Composable
fun MoviesMainScreen(
    onOpenGreedScreen: (type: GreedType) -> Unit,
    onOpenDetailsScreen: (id: Int) -> Unit
) {
    val di = androidContextDI()
    val repository: MoviesRepository by di.instance()
    val stateNow by stateShortProducer(response = repository::getNowPlaying)
    val statePop by stateShortProducer(response = repository::getPopularMovies)
    val stateTop by stateShortProducer(response = repository::getTopRateMovies)

    Scaffold( //TODO аждый экран должен реализовать его сам
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.padding(end = 16.dp)) {
                        Text("Films Library", modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_palette),
                            contentDescription = "Change theme",
                            modifier = Modifier.clickable { }
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                MoviesBigRow(
                    state = stateNow,
                    title = "Movies now playing",
                    onItemClick = onOpenDetailsScreen,
                    onShowAllClick = {
                        onOpenGreedScreen(GreedType.NOW)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.size(12.dp))
            }
            item {
                MoviesRow(state = statePop, title = "What's Popular", onShowAll = {
                    onOpenGreedScreen(GreedType.POP)
                })
            }
            item {
                Spacer(modifier = Modifier.size(12.dp))
            }
            item {
                MoviesRow(state = stateTop, title = "Top movies", onShowAll = {
                    onOpenGreedScreen(GreedType.TOP)
                })
            }
        }
    }
}