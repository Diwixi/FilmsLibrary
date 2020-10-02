package com.diwixis.filmlibrary.presentation.movieList

import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwixis.filmlibrary.domain.PaginationListener
import com.diwixis.filmlibrary.domain.repository.MoviesRepository
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.presentation.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

typealias MovieListResponse = Response<List<Movie>>

internal class MovieGreedViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val rxDisposables = CompositeDisposable()

    private val _movies: MutableLiveData<MovieListResponse> = MutableLiveData()
    val errorLiveData: LiveData<MovieListResponse> = _movies

    private var currentPage: Int = PaginationListener.PAGE_START
    private var isAbleToLoadMore = false

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movies.value = Response.failure(exception)
        isAbleToLoadMore = true
    }

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

    fun loadTopRateMovies(page: Int = 1) = liveData(exceptionHandler + Dispatchers.Main) {
        emit(Response.load())
        isAbleToLoadMore = true
        emit(Response.success(repository.getTopRateMovies(page)))
    }

    fun loadPopularMovies(page: Int = 1) = liveData(exceptionHandler + Dispatchers.Main) {
        emit(Response.load())
        isAbleToLoadMore = true
        emit(Response.success(repository.getPopularMovies(page)))
    }

    override fun onCleared() {
        rxDisposables.dispose()
        super.onCleared()
    }
}