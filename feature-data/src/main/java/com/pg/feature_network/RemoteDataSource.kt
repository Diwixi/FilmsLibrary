package com.pg.feature_network

import com.pg.remote.MovieBean
import com.pg.remote.MoviesBean
import com.pg.remote.Params
import com.pg.remote.TmdbApi

internal class RemoteDataSource(private val api: TmdbApi) {
    suspend fun getTopRate(page: Int): MoviesBean =
        api.getTopRatedMovies(Params.movieParams, page)

    suspend fun getPopular(page: Int): MoviesBean =
        api.getPopularMovies(Params.movieParams, page)

    suspend fun getNowPlaying(page: Int): MoviesBean =
        api.getNowPlayingMovies(Params.movieParams, page)

    suspend fun getMovie(id: Int): MovieBean =
        api.getMovieById(params = Params.movieParams, id = id)
}