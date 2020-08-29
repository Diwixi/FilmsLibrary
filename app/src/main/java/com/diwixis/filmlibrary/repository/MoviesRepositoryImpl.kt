package com.diwixis.filmlibrary.repository

import com.diwixis.filmlibrary.api.Params
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.data.map

class MoviesRepositoryImpl(
    private val db: Database,
    private val api: TmdbApi
) : MoviesRepository {

    override fun getTopRateMovies() = api.getTopRatedMovies(Params.movieParams)
        .map { it.movies.also { list -> list.forEach { item -> item.mode = MODE_TOP } } }
        .doOnSuccess { movies ->
            db.movieDao().insertAll(movies)
        }
        .map { it.map() }
        .onErrorResumeNext {
            db.movieDao().getTop().map { it.map() }
        }

    override fun getPopularMovies() = api.getPopularMovies(Params.movieParams)
        .map { it.movies.also { list -> list.forEach { item -> item.mode = MODE_POP } } }
        .doOnSuccess { movies ->
            db.movieDao().insertAll(movies)
        }
        .map { it.map() }
        .onErrorResumeNext {
            db.movieDao().getPop().map { it.map() }
        }

    override fun getMovieById(movieId: Int) = db.movieDao().getById(movieId).map { it.map() }

    companion object {
        const val MODE_TOP = "MODE_TOP"
        const val MODE_POP = "MODE_POP"
    }
}