package com.diwixis.filmlibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diwixis.filmlibrary.data.MovieBean.Companion.TABLE_MOVIE
import com.diwixis.filmlibrary.domain.Movie
import com.diwixis.filmlibrary.domain.Movies
import com.pg.network.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesBean(
    @ColumnInfo(name = "page")
    @SerialName("page")
    val page: Int,
    @ColumnInfo(name = "results")
    @SerialName("results")
    val movies: List<MovieBean> = emptyList()
)

fun MoviesBean.map() = Movies(page = page, movies = movies.map() { it.map() })

@Serializable
@Entity(tableName = TABLE_MOVIE)
data class MovieBean(
    @PrimaryKey @ColumnInfo(name = COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_NAME_TITLE)
    val title: String? = null,
    @SerialName(COLUMN_NAME_POSTER_PATH)
    @ColumnInfo(name = COLUMN_NAME_POSTER_PATH)
    val posterPath: String? = null,
    @ColumnInfo(name = COLUMN_NAME_OVERVIEW)
    val overview: String? = null,
    @SerialName(COLUMN_NAME_RELEASE_DATE)
    @ColumnInfo(name = COLUMN_NAME_RELEASE_DATE)
    val releaseDate: String? = null,
    @SerialName(COLUMN_NAME_ORDINAL_TITLE)
    @ColumnInfo(name = COLUMN_NAME_ORDINAL_TITLE)
    val originalTitle: String? = null,
    @ColumnInfo(name = COLUMN_NAME_POPULARITY)
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

fun List<MovieBean>.map() = this.map { it.map() }
fun MovieBean.map() = Movie(
    id = id,
    title = title,
    posterPath = "${BuildConfig.API_IMAGE_BASE_URL}${posterPath}",
    overview = overview,
    releaseDate = releaseDate,
    originalTitle = originalTitle,
    popularity = popularity
)