package com.diwixis.filmlibrary.repository

import com.diwixis.filmlibrary.movies_module.Movie
import io.reactivex.Single

/**
 * Created by Diwixis on 19.04.2017.
 */
interface MoviesRepository {
    fun getTopRateMovies(): Single<List<Movie>>
//    fun getpopularMovies(): Single<List<Movie>>
}