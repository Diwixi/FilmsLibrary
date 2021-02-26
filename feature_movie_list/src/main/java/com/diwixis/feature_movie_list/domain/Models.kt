package com.diwixis.feature_movie_list.domain

import androidx.room.ColumnInfo
import com.diwixis.feature_movie_list.data.MovieBean
import com.diwixis.feature_movie_list.data.map
import com.diwixis.feature_movie_list.presentation.Movies
import com.google.gson.annotations.SerializedName

data class MoviesBean(
    @ColumnInfo(name = "page")
    @SerializedName("page") val page: Int,
    @ColumnInfo(name = "results")
    @SerializedName("results") val movies: List<MovieBean> = emptyList()
)

fun MoviesBean.map() = Movies(page = page, movies = movies.map() { it.map() })