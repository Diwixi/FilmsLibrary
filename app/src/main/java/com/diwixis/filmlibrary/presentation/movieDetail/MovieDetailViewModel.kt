package com.diwixis.filmlibrary.presentation.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.domain.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias MovieResponse = Response<Movie>

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movie: MutableLiveData<MovieResponse> = MutableLiveData()
    val errorLiveData: LiveData<MovieResponse> = _movie

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movie.value = Response.failure(exception)
    }

    fun loadMovieById(movieId: Int) = liveData(exceptionHandler + Dispatchers.IO) {
        emit(Response.load())
        val result = withContext(Dispatchers.IO) {
            repository.getMovieById(movieId)
        }
        emit(Response.success(result))
    }
}