package com.diwixis.filmlibrary.api

import com.diwixis.filmlibrary.api.Urls.MOVIE_ID_QUERY
import com.diwixis.filmlibrary.data.MovieBean
import com.diwixis.filmlibrary.data.MoviesBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TmdbApi {
    @GET(Urls.MOVIE_POPULAR)
    suspend fun getPopularMovies(
        @QueryMap params: HashMap<String, String>,
        @Query("page") page: Int
    ): MoviesBean

    @GET(Urls.MOVIE_TOP_RATE)
    suspend fun getTopRatedMovies(
        @QueryMap params: HashMap<String, String>,
        @Query("page") page: Int
    ): MoviesBean

    @GET(Urls.MOVIE_NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @QueryMap params: HashMap<String, String>,
        @Query("page") page: Int
    ): MoviesBean

    @GET(Urls.MOVIE_BY_ID)
    suspend fun getMovieById(
        @Path(MOVIE_ID_QUERY) id: Int,
        @QueryMap params: HashMap<String, String>
    ): MovieBean
}