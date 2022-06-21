package com.pg.feature_main

import com.pg.feature_network.Movie
import com.pg.feature_network.usecase.GetNowPlayingMoviesUseCase
import com.pg.feature_network.usecase.GetPopMoviesUseCase
import com.pg.feature_network.usecase.GetTopMoviesUseCase

class MainUseCase {

    suspend fun getTopMovies(): List<Movie> =
        GetTopMoviesUseCase()(PAGE).take(MOVIES_NUMBER)

    suspend fun getPopMovies(): List<Movie> =
        GetPopMoviesUseCase()(PAGE).take(MOVIES_NUMBER)

    suspend fun getNowPlayingMovies(): List<Movie> =
        GetNowPlayingMoviesUseCase()(PAGE).take(MOVIES_NUMBER)

    companion object {
        private const val PAGE = 1
        private const val MOVIES_NUMBER = 5
    }
}
