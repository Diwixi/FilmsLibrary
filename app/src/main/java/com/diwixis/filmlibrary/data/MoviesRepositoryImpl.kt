package com.diwixis.filmlibrary.data

import com.diwixis.filmlibrary.api.Params
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.MoviesRepository

class MoviesRepositoryImpl(
//    private val db: Database,
    private val api: TmdbApi
) : MoviesRepository {

    //TODO переделать
    override suspend fun getTopRateMovies(page: Int): List<Movie> = api
        .getTopRatedMovies(Params.getParams(page)).movies
//        .also { db.movieDao().insertAll(it) }
        .map { it.map() }

    //TODO переделать
    override suspend fun getPopularMovies(page: Int) = api
        .getPopularMovies(Params.getParams(page)).movies
//        .also { db.movieDao().insertAll(it) }
        .map { it.map() }

    override suspend fun getNowPlaying(page: Int) = api
        .getNowPlayingMovies(Params.getParams(page)).movies
//        .also { db.movieDao().insertAll(it) }
        .map { it.map() }

//    override suspend fun getMovieById(movieId: Int) =
//        db.movieDao().getById(movieId).map()
}