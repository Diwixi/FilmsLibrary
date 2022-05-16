package com.diwixis.filmlibrary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwixis.filmlibrary.domain.usecases.MovieDetailsUseCase
import com.diwixis.filmlibrary.presentation.MovieState
import com.diwixis.filmlibrary.presentation.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: MovieDetailsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<MovieState>(UiState.Loading)
    val state: StateFlow<MovieState> = _state
    var fetchJob: Job? = null

    fun fetchMovie(movieId: Int?) {
        if (movieId == null) {
            _state.update { UiState.Error("Wrong type!") }
            return
        }

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val newMovies = useCase(movieId)
            _state.update { UiState.Data(data = newMovies) }
        }
    }
}