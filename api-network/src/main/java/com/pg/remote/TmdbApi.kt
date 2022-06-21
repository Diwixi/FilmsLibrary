package com.pg.remote

import com.pg.remote.Urls.MOVIE_ID_QUERY
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