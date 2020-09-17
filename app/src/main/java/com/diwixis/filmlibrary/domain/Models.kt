package com.diwixis.filmlibrary.domain

import androidx.room.ColumnInfo
import com.diwixis.filmlibrary.data.MovieBean
import com.diwixis.filmlibrary.data.map
import com.diwixis.filmlibrary.presentation.Movies
import com.google.gson.annotations.SerializedName

data class MoviesBean(
    @ColumnInfo(name = "page")
    @SerializedName("page") val page: Int,
    @ColumnInfo(name = "results")
    @SerializedName("results") val movies: List<MovieBean> = emptyList()
)

fun MoviesBean.map() = Movies(page = page, movies = movies.map() { it.map() })