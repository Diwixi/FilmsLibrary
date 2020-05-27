package com.diwixis.filmlibrary.presentation.movieList

import com.diwixis.filmlibrary.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by Diwixis on 19.04.2017.
 */
class MovieGreedPresenter(
    private val repository: MoviesRepository
) {
    lateinit var movieGreedView: MovieGreedView
    private val rxDisposables = CompositeDisposable()

    fun showTopRateMovies() {
        repository.getTopRateMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { movieGreedView.showLoad() }
            .subscribe(
                { results ->
                    movieGreedView.showMovie(results)
                    movieGreedView.hideLoad()
                },
                {
                    it.toString()
                }
            ).addTo(rxDisposables)
    }

    fun showPopularMovies() {
        repository.getpopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { movieGreedView.showLoad() }
            .subscribe(
                { results ->
                    movieGreedView.showMovie(results)
                    movieGreedView.hideLoad()
                },
                {
                    it.toString()
                }
            ).addTo(rxDisposables)
    }
}