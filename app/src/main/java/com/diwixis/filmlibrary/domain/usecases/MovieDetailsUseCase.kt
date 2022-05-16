package com.diwixis.filmlibrary.domain.usecases

import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.MoviesRepository

class MovieDetailsUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movieId: Int): Movie = repository.getMovieById(movieId)
}