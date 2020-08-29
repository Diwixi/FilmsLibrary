package com.diwixis.filmlibrary.repository

import com.diwixis.filmlibrary.presentation.Movie
import io.reactivex.Single

interface MoviesRepository {
    fun getTopRateMovies(): Single<List<Movie>>
    fun getMovieById(movieId: Int): Single<Movie>
    fun getPopularMovies(): Single<List<Movie>>
}