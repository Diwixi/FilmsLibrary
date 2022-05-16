package com.diwixis.filmlibrary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwixis.filmlibrary.domain.usecases.MainUseCase
import com.diwixis.filmlibrary.presentation.MovieListState
import com.diwixis.filmlibrary.presentation.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase) : ViewModel() {
    private val _topState = MutableStateFlow<MovieListState>(UiState.Loading)
    val topState: StateFlow<MovieListState> = _topState
    private var fetchTopJob: Job? = null

    private val _popState = MutableStateFlow<MovieListState>(UiState.Loading)
    val popState: StateFlow<MovieListState> = _popState
    private var fetchPopJob: Job? = null

    private val _nowPlayingState = MutableStateFlow<MovieListState>(UiState.Loading)
    val nowPlayingState: StateFlow<MovieListState> = _nowPlayingState
    private var fetchNowJob: Job? = null

    fun fetchTopMovies() {
        fetchTopJob?.cancel()
        fetchTopJob = viewModelScope.launch {
            val newMovies = useCase.getTopMovies()
            _topState.update { UiState.Data(data = newMovies) }
        }
    }

    fun fetchPopMovies() {
        fetchPopJob?.cancel()
        fetchPopJob = viewModelScope.launch {
            val newMovies = useCase.getPopMovies()
            _popState.update { UiState.Data(data = newMovies) }
        }
    }

    fun fetchNowPlayingMovies() {
        fetchNowJob?.cancel()
        fetchNowJob = viewModelScope.launch {
            val newMovies = useCase.getNowPlayingMovies()
            _nowPlayingState.update { UiState.Data(data = newMovies) }
        }
    }
}