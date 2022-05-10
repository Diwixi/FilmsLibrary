package com.diwixis.filmlibrary.domain

data class Movies(
    val page: Int,
    val movies: List<Movie> = emptyList()
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