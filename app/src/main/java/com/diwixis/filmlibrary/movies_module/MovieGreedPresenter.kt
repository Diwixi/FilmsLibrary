package com.diwixis.filmlibrary.movies_module

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

    fun init(isTopRated: Boolean) {
        repository.getTopRateMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { movieGreedView.showLoad() }
            .subscribe(
                { results ->
                    movieGreedView.showMovie(results)
                },
                {
                    it.toString()
                }
            ).addTo(rxDisposables)
    }

    fun update(isTopRated: Boolean) {
//        RepositoryProvider.providerRepository()
//            .movies(isTopRated)
//            .subscribe({ results: List<Result?>? ->
//                movieGreedView.showMovie(
//                    results
//                )
//            })
    }
}