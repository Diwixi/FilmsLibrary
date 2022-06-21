package com.pg.feature_network.usecase

import com.pg.feature_network.Movie
import com.pg.feature_network.MoviesRepository

class GetPopMoviesUseCase {
    private val repository: MoviesRepository by get()
    suspend operator fun invoke(page: Int): List<Movie> = repository.getPopularMovies(page)
}