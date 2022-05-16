package com.diwixis.filmlibrary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwixis.filmlibrary.domain.usecases.MovieListUseCase
import com.diwixis.filmlibrary.navigation.GreedType
import com.diwixis.filmlibrary.presentation.MovieListState
import com.diwixis.filmlibrary.presentation.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    private val _state = MutableStateFlow<MovieListState>(UiState.Loading)
    val state: StateFlow<MovieListState> = _state
    private var fetchJob: Job? = null

    fun fetchMovies(type: GreedType?) {
        if (type == null) {
            _state.update { UiState.Error("Wrong type!") }
            return
        }

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val newMovies = when (type) {
                GreedType.TOP -> useCase.getTopMovies()
                GreedType.POP -> useCase.getPopMovies()
                GreedType.NOW -> useCase.getNowPlayingMovies()
            }
            _state.update { UiState.Data(data = newMovies) }
        }
    }
}