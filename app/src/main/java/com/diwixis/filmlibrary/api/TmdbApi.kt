package com.diwixis.filmlibrary.api

import com.diwixis.filmlibrary.data.MoviesBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

interface TmdbApi {
    @GET(Urls.MOVIE_POPULAR)
    fun getPopularMovies(@QueryMap params: HashMap<String, String>): Single<MoviesBean>

    @GET(Urls.MOVIE_TOP_RATE)
    fun getTopRatedMovies(@QueryMap params: HashMap<String, String>): Single<MoviesBean>
}