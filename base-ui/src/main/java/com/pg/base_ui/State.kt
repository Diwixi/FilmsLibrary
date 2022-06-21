package com.pg.base_ui

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    class Error(val message: String) : UiState<Nothing>()
    class Data<out T : Any>(val data: T) : UiState<T>()
}