package com.diwixis.filmlibrary.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwixis.filmlibrary.domain.PaginationListener
import com.diwixis.filmlibrary.domain.repository.MoviesRepository
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.presentation.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

typealias MovieResponse = Response<List<Movie>>

internal class MovieGreedViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movies: MutableLiveData<MovieResponse> = MutableLiveData()

    private var currentPage: Int = PaginationListener.PAGE_START
    private var isAbleToLoadMore = false

    fun getPaginationListener(
        manager: LinearLayoutManager,
        mode: MovieListFragment.MoviesMode
    ): PaginationListener {
        return object : PaginationListener(manager) {
            override fun loadMoreItems() {
                currentPage++
                this@MovieGreedViewModel.isAbleToLoadMore = false
                when (mode) {
                    MovieListFragment.MoviesMode.TOP -> loadTopRateMovies(currentPage)
                    MovieListFragment.MoviesMode.POP -> loadPopularMovies(currentPage)
                }
            }

            override val isAbleToLoadMore: Boolean
                get() = this@MovieGreedViewModel.isAbleToLoadMore
        }
    }

    fun loadTopRateMovies(page: Int = 1): LiveData<MovieResponse> {
        repository.getTopRateMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movies.value = Response.load() }
            .subscribe(
                { results ->
                    _movies.value = Response.success(results)
                    isAbleToLoadMore = true
                },
                {
                    _movies.value = Response.failure(it)
                    isAbleToLoadMore = true
                }
            ).addTo(rxDisposables)
        return _movies
    }

    fun loadPopularMovies(page: Int = 1): LiveData<MovieResponse> {
        repository.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movies.value = Response.load() }
            .subscribe(
                { results ->
                    _movies.value = Response.success(results)
                    isAbleToLoadMore = true
                },
                {
                    _movies.value = Response.failure(it)
                    isAbleToLoadMore = true
                }
            ).addTo(rxDisposables)
        return _movies
    }

    override fun onCleared() {
        rxDisposables.dispose()
        super.onCleared()
    }
}