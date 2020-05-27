package com.diwixis.filmlibrary.presentation

import java.io.Serializable

data class Movies(
    val page: Int,
    val movies: List<Movie> = emptyList()
) : Serializable

data class Movie(
    var id: Int,
    val title: String? = null,
    val posterPath: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val originalTitle: String? = null,
    val popularity: Double? = null
) : Serializable