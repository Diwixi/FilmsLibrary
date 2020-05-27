package com.diwixis.filmlibrary.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class MovieGreedViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movies: MutableLiveData<Response<List<Movie>>> = MutableLiveData()
    val movies: LiveData<Response<List<Movie>>> = _movies

    fun loadTopRateMovies() {
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
    }

    fun loadPopularMovies() {
        repository.getpopularMovies()
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
    }

    override fun onCleared() {
        rxDisposables.dispose()
        super.onCleared()
    }
}