package com.diwixis.filmlibrary.domain.repository

import com.diwixis.filmlibrary.presentation.Movie
import io.reactivex.Single

interface MoviesRepository {
    suspend fun getTopRateMovies(page: Int = 1): List<Movie>
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie
}