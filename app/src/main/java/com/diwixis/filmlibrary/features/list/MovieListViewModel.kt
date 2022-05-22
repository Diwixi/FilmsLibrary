package com.diwixis.filmlibrary.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwixis.filmlibrary.domain.usecases.MovieListUseCase
import com.diwixis.filmlibrary.navigation.ListType
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

    fun fetchMovies(type: ListType?) {
        if (type == null) {
            _state.update { UiState.Error("Wrong type!") }
            return
        }

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val newMovies = when (type) {
                ListType.TOP -> useCase.getTopMovies()
                ListType.POP -> useCase.getPopMovies()
                ListType.NOW -> useCase.getNowPlayingMovies()
            }
            _state.update { UiState.Data(data = newMovies) }
        }
    }
}