package com.pg.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesBean(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movies: List<MovieBean> = emptyList()
)

@Serializable
data class MovieBean(
    val id: Int,
    val title: String? = null,
    @SerialName(COLUMN_NAME_POSTER_PATH)
    val posterPath: String? = null,
    val overview: String? = null,
    @SerialName(COLUMN_NAME_RELEASE_DATE)
    val releaseDate: String? = null,
    @SerialName(COLUMN_NAME_ORDINAL_TITLE)
    val originalTitle: String? = null,
    val popularity: Double? = null
) {
    companion object {
        const val TABLE_MOVIE = "movie"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_POSTER_PATH = "poster_path"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_RELEASE_DATE = "release_date"
        const val COLUMN_NAME_ORDINAL_TITLE = "original_title"
        const val COLUMN_NAME_POPULARITY = "popularity"
    }
}