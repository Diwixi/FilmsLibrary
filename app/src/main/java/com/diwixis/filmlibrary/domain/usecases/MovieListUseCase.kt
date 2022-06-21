package com.diwixis.filmlibrary.domain.usecases

import com.diwixis.filmlibrary.domain.Movie
import com.pg.remote.MoviesRepository

class MovieListUseCase(private val repository: com.pg.remote.MoviesRepository) {
    suspend fun getTopMovies(): List<Movie> = com.pg.remote.GetTopMoviesUseCase(repository)(1)

    suspend fun getPopMovies(): List<Movie> = com.pg.remote.GetPopMoviesUseCase(repository)(1)

    suspend fun getNowPlayingMovies(): List<Movie> = com.pg.remote.GetNowPlayingMoviesUseCase(
        repository
    )(1)
}