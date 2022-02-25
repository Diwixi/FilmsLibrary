package com.diwixis.filmlibrary.presentation.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import com.diwixis.filmlibrary.domain.Movie
import com.pg.network.Failure
import com.pg.network.Success

@Composable
fun stateShortProducer(response: suspend () -> List<Movie>): State<MovieListState> {
    return produceState(initialValue = MovieListState(), producer = {
        com.pg.network.response { response() }.also {
            value = when (it) {
                is Success -> {
                    MovieListState(loading = false, movies = it.value.take(5), error = null)
                }
                is Failure -> {
                    MovieListState(loading = false, error = it.error.message)
                }
            }
        }
    })
}