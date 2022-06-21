package com.pg.feature_details.usecase

import com.pg.model.Movie
import com.pg.remote.MoviesRepository

class MovieDetailsUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movieId: Int): Movie = repository.getMovieById(movieId)
}