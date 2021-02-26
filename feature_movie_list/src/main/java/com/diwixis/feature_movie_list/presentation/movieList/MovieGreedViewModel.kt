package com.diwixis.feature_movie_list.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwixis.feature_movie_list.domain.PaginationListener
import com.diwixis.feature_movie_list.domain.repository.MoviesRepository
import com.diwixis.feature_movie_list.domain.utils.Response
import com.diwixis.feature_movie_list.presentation.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

typealias MovieListResponse = Response<List<Movie>>

internal class MovieGreedViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _error: MutableLiveData<MovieListResponse> = MutableLiveData()
    val errorLiveData: LiveData<MovieListResponse> = _error

    private var currentPage: Int = PaginationListener.PAGE_START
    private var isAbleToLoadMore = false

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.value = Response.failure(exception)
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
}