package com.diwixis.filmlibrary.data.datasource

import com.diwixis.filmlibrary.api.Params
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.data.MovieBean

class RemoteDataSource(
    private val api: TmdbApi
) {
    suspend fun getTopRate(page: Int): List<MovieBean> =
        api.getTopRatedMovies(Params.getParams(page)).movies

    suspend fun getPopular(page: Int): List<MovieBean> =
        api.getPopularMovies(Params.getParams(page)).movies

    suspend fun getNowPlaying(page: Int): List<MovieBean> =
        api.getNowPlayingMovies(Params.getParams(page)).movies
}