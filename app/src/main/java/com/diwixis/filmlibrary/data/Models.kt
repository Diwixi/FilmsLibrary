package com.diwixis.filmlibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diwixis.filmlibrary.api.Urls
import com.diwixis.filmlibrary.data.MovieBean.Companion.TABLE_MOVIE_PAGE
import com.diwixis.filmlibrary.movies_module.Movie
import com.diwixis.filmlibrary.movies_module.Movies
import com.google.gson.annotations.SerializedName

/**
 * Room models
 *
 * @author П. Густокашин (Diwixis)
 */
@Entity(tableName = TABLE_MOVIE_PAGE)
data class MoviesBean(
    @PrimaryKey @ColumnInfo(name = "page")
    @SerializedName("page") val page: Int,
    @ColumnInfo(name = "results")
    @SerializedName("results") val movies: List<MovieBean> = emptyList()
)

fun MoviesBean.map() = Movies(page = page, movies = movies.map { it.map() })

data class MovieBean(
    @ColumnInfo(name = COLUMN_NAME_ID)
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
    @SerializedName(COLUMN_NAME_RELEASE_DATE)
    val releaseDate: String? = null,
    @SerializedName(COLUMN_NAME_ORDINAL_TITLE)
    val originalTitle: String? = null,
    @SerializedName(COLUMN_NAME_POPULARITY)
    val popularity: Double? = null
) {
    companion object {
        const val TABLE_MOVIE = "movie"
        const val TABLE_MOVIE_PAGE = "movie_page"
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
    posterPath = "${Urls.BASE}${posterPath}",
    overview = overview
)