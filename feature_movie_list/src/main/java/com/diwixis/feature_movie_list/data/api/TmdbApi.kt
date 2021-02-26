package com.diwixis.feature_movie_list.data.api

import com.diwixis.feature_movie_list.domain.MoviesBean
import com.diwixis.feature_movie_list.domain.utils.Urls
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

internal interface TmdbApi {
    @GET(Urls.MOVIE_POPULAR)
    suspend fun getPopularMovies(@QueryMap params: HashMap<String, String>): MoviesBean

    @GET(Urls.MOVIE_TOP_RATE)
    suspend fun getTopRatedMovies(@QueryMap params: HashMap<String, String>): MoviesBean
}