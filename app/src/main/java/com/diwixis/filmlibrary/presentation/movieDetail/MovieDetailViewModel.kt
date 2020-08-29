package com.diwixis.filmlibrary.presentation.movieDetail

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

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movie: MutableLiveData<Response<Movie>> = MutableLiveData()

    fun loadMovieById(movieId: Int): LiveData<Response<Movie>> {
        repository.getMovieById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movie.value = Response.load() }
            .subscribe(
                { results ->
                    _movie.value = Response.success(results)
                },
                {
                    //TODO add error
                    _movie.value = Response.failure(it)
                }
            ).addTo(rxDisposables)
        return _movie
    }
}