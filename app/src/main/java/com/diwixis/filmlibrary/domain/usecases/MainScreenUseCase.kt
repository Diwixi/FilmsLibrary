package com.diwixis.filmlibrary.domain.usecases

import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.MoviesRepository

class MainScreenUseCase(private val repository: MoviesRepository) {

    suspend fun getTopMovies(): List<Movie> =
        GetTopMoviesUseCase(repository)(PAGE).take(MOVIES_NUMBER)

    suspend fun getPopMovies(): List<Movie> =
        GetPopMoviesUseCase(repository)(PAGE).take(MOVIES_NUMBER)

    suspend fun getNowPlayingMovies(): List<Movie> =
        GetNowPlayingMoviesUseCase(repository)(PAGE).take(MOVIES_NUMBER)

    companion object {
        private const val PAGE = 1
        private const val MOVIES_NUMBER = 5
    }
}