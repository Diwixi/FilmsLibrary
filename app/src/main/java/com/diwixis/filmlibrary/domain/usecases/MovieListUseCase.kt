package com.diwixis.filmlibrary.domain.usecases

import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.MoviesRepository

class MovieListUseCase(private val repository: MoviesRepository) {
    suspend fun getTopMovies(): List<Movie> = GetTopMoviesUseCase(repository)(1)

    suspend fun getPopMovies(): List<Movie> = GetPopMoviesUseCase(repository)(1)

    suspend fun getNowPlayingMovies(): List<Movie> = GetNowPlayingMoviesUseCase(repository)(1)
}