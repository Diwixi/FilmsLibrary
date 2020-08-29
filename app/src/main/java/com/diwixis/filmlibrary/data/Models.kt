package com.diwixis.filmlibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.diwixis.filmlibrary.BuildConfig
import com.diwixis.filmlibrary.data.MovieBean.Companion.TABLE_MOVIE
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.presentation.Movies
import com.google.gson.annotations.SerializedName

data class MoviesBean(
    @ColumnInfo(name = "page")
    @SerializedName("page") val page: Int,
    @ColumnInfo(name = "results")
    @SerializedName("results") val movies: List<MovieBean> = emptyList()
)

fun MoviesBean.map() = Movies(page = page, movies = movies.map { it.map() })

@Entity(tableName = TABLE_MOVIE)
data class MovieBean(
    @PrimaryKey @ColumnInfo(name = COLUMN_NAME_ID)
    @SerializedName(COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_NAME_TITLE)
    @SerializedName(COLUMN_NAME_TITLE)
    val title: String? = null,
    @ColumnInfo(name = COLUMN_NAME_POSTER_PATH)
    @SerializedName(COLUMN_NAME_POSTER_PATH)
    val posterPath: String? = null,
    @ColumnInfo(name = COLUMN_NAME_OVERVIEW)
    @SerializedName(COLUMN_NAME_OVERVIEW)
    val overview: String? = null,
    @ColumnInfo(name = COLUMN_NAME_RELEASE_DATE)
    @SerializedName(COLUMN_NAME_RELEASE_DATE)
    val releaseDate: String? = null,
    @ColumnInfo(name = COLUMN_NAME_ORDINAL_TITLE)
    @SerializedName(COLUMN_NAME_ORDINAL_TITLE)
    val originalTitle: String? = null,
    @ColumnInfo(name = COLUMN_NAME_POPULARITY)
    @SerializedName(COLUMN_NAME_POPULARITY)
    val popularity: Double? = null,
    @ColumnInfo(name = COLUMN_NAME_MODE)
    @SerializedName(COLUMN_NAME_MODE)
    var mode: String? = null // I can't thing anything better. This for separate TOP and POPULAR
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
        const val COLUMN_NAME_MODE = "mode"
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