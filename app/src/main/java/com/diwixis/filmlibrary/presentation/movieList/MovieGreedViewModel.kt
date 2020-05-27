package com.diwixis.filmlibrary.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class MovieGreedViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

//    val topRate = liveData<Response<List<Movie>>> {
//        repository.getTopRateMovies()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { results ->
//                    emit(Response.success(results))
//                },
//                {
//                    emit(Response.failure(it))
//                }
//            ).addTo(rxDisposables)
//    }

    fun showTopRateMovies() {
        repository.getTopRateMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { results ->
                    _movies.value = results
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
//            .doOnSubscribe { movieGreedView.showLoad() }
            .subscribe(
                { results ->
                    _movies.value = results
//                    movieGreedView.hideLoad()
                },
                {
                    it.toString()
                }
            ).addTo(rxDisposables)
    }

    override fun onCleared() {
        rxDisposables.dispose()
        super.onCleared()
    }
}