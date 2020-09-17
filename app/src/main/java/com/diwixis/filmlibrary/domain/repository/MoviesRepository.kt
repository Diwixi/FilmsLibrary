package com.diwixis.filmlibrary.domain.repository

import com.diwixis.filmlibrary.presentation.Movie
import io.reactivex.Single

interface MoviesRepository {
    fun getTopRateMovies(page: Int = 1): Single<List<Movie>>
    fun getMovieById(movieId: Int): Single<Movie>
    fun getPopularMovies(page: Int = 1): Single<List<Movie>>
}