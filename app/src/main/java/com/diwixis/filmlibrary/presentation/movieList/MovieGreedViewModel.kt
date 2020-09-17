package com.diwixis.filmlibrary.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.domain.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

typealias MovieResponse = Response<List<Movie>>

internal class MovieGreedViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movies: MutableLiveData<MovieResponse> = MutableLiveData()

    fun loadTopRateMovies(page: Int = 1): LiveData<MovieResponse> {
        repository.getTopRateMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movies.value = Response.load() }
            .subscribe(
                { results ->
                    _movies.value = Response.success(results)
                },
                {
                    _movies.value = Response.failure(it)
                }
            ).addTo(rxDisposables)
        return _movies
    }

    fun loadPopularMovies(): LiveData<MovieResponse> {
        repository.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movies.value = Response.load() }
            .subscribe(
                { results ->
                    _movies.value = Response.success(results)
                },
                {
                    _movies.value = Response.failure(it)
                }
            ).addTo(rxDisposables)
        return _movies
    }

    override fun onCleared() {
        rxDisposables.dispose()
        super.onCleared()
    }
}