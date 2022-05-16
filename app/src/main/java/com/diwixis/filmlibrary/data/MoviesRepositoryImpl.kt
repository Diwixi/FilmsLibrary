package com.diwixis.filmlibrary.data

import com.diwixis.filmlibrary.data.datasource.LocalDataSource
import com.diwixis.filmlibrary.data.datasource.RemoteDataSource
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.MoviesRepository

class MoviesRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MoviesRepository {

    // TODO: рассмотреть возможность хранить данные тут
//    val movies: Flow<Movie> = ...

    //TODO добавить ипользование localDataSource
    override suspend fun getTopRateMovies(page: Int): List<Movie> =
        remoteDataSource.getTopRate(page)
//        .also { db.movieDao().insertAll(it) }
            .map { it.map() }

    //TODO добавить ипользование localDataSource
    override suspend fun getPopularMovies(page: Int) =
        remoteDataSource.getPopular(page)
//        .also { db.movieDao().insertAll(it) }
            .map { it.map() }

    //TODO добавить ипользование localDataSource
    override suspend fun getNowPlayingMovies(page: Int) =
        remoteDataSource.getNowPlaying(page)
//        .also { db.movieDao().insertAll(it) }
            .map { it.map() }

    override suspend fun getMovieById(movieId: Int) =
        remoteDataSource.getMovie(movieId)
//            .also { db.movieDao().insert(it) }
            .map()
}