package com.diwixis.filmlibrary.data

import com.diwixis.filmlibrary.domain.utils.Params
import com.diwixis.filmlibrary.data.api.TmdbApi
import com.diwixis.filmlibrary.domain.map
import com.diwixis.filmlibrary.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val db: Database,
    private val api: TmdbApi
) : MoviesRepository {

    override suspend fun getTopRateMovies(page: Int) = api
        .getTopRatedMovies(Params.getParams(page)).movies.apply {
            forEach { item -> item.mode = MODE_TOP }
        }
        .also { db.movieDao().insertAll(it) }
        .map { it.map() }

    override suspend fun getPopularMovies(page: Int) = api
        .getPopularMovies(Params.getParams(page)).movies.apply {
            forEach { item -> item.mode = MODE_POP }
        }
        .also { db.movieDao().insertAll(it) }
        .map { it.map() }

    override suspend fun getMovieById(movieId: Int) =
        db.movieDao().getById(movieId).map()

    companion object {
        const val MODE_TOP = "MODE_TOP"
        const val MODE_POP = "MODE_POP"
    }
}