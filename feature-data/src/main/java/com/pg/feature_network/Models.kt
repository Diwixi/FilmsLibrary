package com.pg.feature_network

import com.pg.remote.MovieBean
import com.pg.remote.MoviesBean

data class Movies(
    val page: Int,
    val movies: List<Movie> = emptyList()
)

internal fun MoviesBean.toMovies() = Movies(
    page = page,
    movies = movies.map { it.toMovie() }
)

data class Movie(
    var id: Int,
    val title: String? = null,
    val originalTitle: String? = null,
    val posterPath: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null
) {
    val name: String
        get() = title ?: originalTitle ?: "[NO TITLE]"
}

internal fun MovieBean.toMovie() = Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    posterPath = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    popularity = popularity
)