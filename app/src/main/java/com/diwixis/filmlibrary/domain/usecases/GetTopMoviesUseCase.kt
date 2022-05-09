package com.diwixis.filmlibrary.domain.usecases

import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.MoviesRepository

class GetTopMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(page: Int): List<Movie> = repository.getTopRateMovies(page)
}