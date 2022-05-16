package com.diwixis.filmlibrary.presentation

import com.diwixis.filmlibrary.domain.Movie

typealias MovieListState = UiState<List<Movie>>
typealias MovieState = UiState<Movie>

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    class Error(val message: String) : UiState<Nothing>()
    class Data<out T : Any>(val data: T) : UiState<T>()
}