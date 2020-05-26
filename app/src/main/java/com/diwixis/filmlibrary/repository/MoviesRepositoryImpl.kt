package com.diwixis.filmlibrary.repository

import com.diwixis.filmlibrary.Params
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.data.map

/**
 * Created by Diwixis on 19.04.2017.
 */
class MoviesRepositoryImpl(
    private val db: Database,
    private val api: TmdbApi
) : MoviesRepository {

    override fun getTopRateMovies() = api.getTopRatedMovies(Params.movieParams)
        .map { it.movies }
        .doOnSuccess { movies ->
            db.movieDao().deleteAll()
            db.movieDao().insertAll(movies)
        }
        .map { it.map() }
        .onErrorResumeNext {
            db.movieDao().getAll().map { it.map() }
        }

    override fun getpopularMovies() = api.getPopularMovies(Params.movieParams)
        .map { it.movies }
        .doOnSuccess { movies ->
            db.movieDao().deleteAll()
            db.movieDao().insertAll(movies)
        }
        .map { it.map() }
        .onErrorResumeNext {
            db.movieDao().getAll().map { it.map() }
        }

    override fun getmovieById(movieId: Int) = db.movieDao().getById(movieId).map { it.map() }

}