package com.diwixis.feature_movie_list.domain.repository

import com.diwixis.feature_movie_list.presentation.Movie

interface MoviesRepository {
    suspend fun getTopRateMovies(page: Int = 1): List<Movie>
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie
}