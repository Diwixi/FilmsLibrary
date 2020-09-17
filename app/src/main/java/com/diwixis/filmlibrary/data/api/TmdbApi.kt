package com.diwixis.filmlibrary.data.api

import com.diwixis.filmlibrary.domain.MoviesBean
import com.diwixis.filmlibrary.domain.utils.Urls
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