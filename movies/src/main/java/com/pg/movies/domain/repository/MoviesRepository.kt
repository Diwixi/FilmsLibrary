package com.pg.movies.domain.repository

import com.pg.movies.domain.Movie

interface MoviesRepository {
    suspend fun getTopRateMovies(page: Int = 1): List<Movie>
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie
}