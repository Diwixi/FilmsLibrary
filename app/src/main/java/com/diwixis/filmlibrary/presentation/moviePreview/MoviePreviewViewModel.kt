package com.diwixis.filmlibrary.presentation.moviePreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.diwixis.filmlibrary.domain.repository.MoviesRepository
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.presentation.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias MovieResponse = Response<Movie>

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _error: MutableLiveData<MovieResponse> = MutableLiveData()
    val errorLiveData: LiveData<MovieResponse> = _error

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.value = Response.failure(exception)
    }

    fun loadMovieById(movieId: Int) = liveData(exceptionHandler + Dispatchers.IO) {
        emit(Response.load())
        val result = withContext(Dispatchers.IO) {
            repository.getMovieById(movieId)
        }
        emit(Response.success(result))
    }
}