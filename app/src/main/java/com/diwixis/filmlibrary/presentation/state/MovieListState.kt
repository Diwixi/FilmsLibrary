package com.diwixis.filmlibrary.presentation.state

import com.diwixis.filmlibrary.domain.Movie

sealed class MovieListState {
    object Loading : MovieListState()
    class Error(val message: String) : MovieListState()
    class Data(val data: List<Movie>) : MovieListState()
}