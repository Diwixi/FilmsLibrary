package com.diwixis.filmlibrary.presentation.state

import com.diwixis.filmlibrary.domain.Movie

data class MovieListState(
    val loading: Boolean = true,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)