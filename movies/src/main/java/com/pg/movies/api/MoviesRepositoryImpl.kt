package com.pg.movies.api

import com.pg.movies.data.Database
import com.pg.movies.data.map
import com.pg.movies.domain.Movie
import com.pg.movies.domain.repository.MoviesRepository
import com.pg.movies.domain.utils.Params
import com.pg.network.TmdbApi

class MoviesRepositoryImpl(
    private val db: Database,
    private val api: TmdbApi
) : MoviesRepository {

    //переделать. Просто сохранять ответ в бд и возвращать
    override suspend fun getTopRateMovies(page: Int) = emptyList<Movie>()
//    = api
//        .getTopRatedMovies(Params.getParams(page)).movies
//        .onEach { item -> item.mode = MODE_TOP }
//        .also { db.movieDao().insertAll(it) }
//        .map { it.map() }
//
    override suspend fun getPopularMovies(page: Int) = emptyList<Movie>()
//    = api
//        .getPopularMovies(Params.getParams(page)).movies
//        .onEach { item -> item.mode = MODE_POP }
//        .also { db.movieDao().insertAll(it) }
//        .map { it.map() }

    override suspend fun getMovieById(movieId: Int) =
        db.movieDao().getById(movieId).map()

    companion object {
        const val MODE_TOP = "MODE_TOP"
        const val MODE_POP = "MODE_POP"
    }
}