package com.pg.base_ui

typealias Action = () -> Unit
typealias DataAction<T> = (T) -> Unit

typealias <T>MovieState = UiState<T>
typealias <T>MovieListState = UiState<List<T>>