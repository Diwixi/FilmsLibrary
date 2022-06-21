package com.pg.feature_network

internal class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    // TODO: рассмотреть возможность хранить данные тут
//    val movies: Flow<Movie> = ...

    //TODO добавить ипользование localDataSource
    suspend fun getTopRateMovies(page: Int): List<Movie> =
        remoteDataSource.getTopRate(page).toMovies().movies

    //TODO добавить ипользование localDataSource
    suspend fun getPopularMovies(page: Int) =
        remoteDataSource.getPopular(page).toMovies().movies

    //TODO добавить ипользование localDataSource
    suspend fun getNowPlayingMovies(page: Int) =
        remoteDataSource.getNowPlaying(page).toMovies().movies

    suspend fun getMovieById(movieId: Int) = remoteDataSource.getMovie(movieId).toMovie()
}