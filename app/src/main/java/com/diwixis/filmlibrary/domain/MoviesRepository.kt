package com.diwixis.filmlibrary.domain

interface MoviesRepository {
    suspend fun getTopRateMovies(page: Int = 1): List<Movie>
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getNowPlayingMovies(page: Int = 1): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie
}