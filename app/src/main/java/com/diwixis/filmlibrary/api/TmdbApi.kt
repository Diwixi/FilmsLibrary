package com.diwixis.filmlibrary.api

import com.diwixis.filmlibrary.data.MoviesBean
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

interface TmdbApi {
    @GET(Urls.MOVIE_POPULAR)
    suspend fun getPopularMovies(@QueryMap params: HashMap<String, String>): MoviesBean

    @GET(Urls.MOVIE_TOP_RATE)
    suspend fun getTopRatedMovies(@QueryMap params: HashMap<String, String>): MoviesBean

    @GET(Urls.MOVIE_NOW_PLAYING)
    suspend fun getNowPlayingMovies(@QueryMap params: HashMap<String, String>): MoviesBean
}