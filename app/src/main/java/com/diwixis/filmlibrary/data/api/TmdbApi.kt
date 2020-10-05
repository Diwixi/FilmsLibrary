package com.diwixis.filmlibrary.data.api

import com.diwixis.filmlibrary.domain.MoviesBean
import com.diwixis.filmlibrary.domain.utils.Urls
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

interface TmdbApi {
    @GET(Urls.MOVIE_POPULAR)
    suspend fun getPopularMovies(@QueryMap params: HashMap<String, String>): MoviesBean

    @GET(Urls.MOVIE_TOP_RATE)
    suspend fun getTopRatedMovies(@QueryMap params: HashMap<String, String>): MoviesBean
}