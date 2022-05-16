package com.diwixis.filmlibrary.api

object Urls {
    private const val API_VERSION = 3
    const val MOVIE_ID_QUERY = "movie_id"
    const val MOVIE_POPULAR = "/$API_VERSION/movie/popular"
    const val MOVIE_TOP_RATE = "/$API_VERSION/movie/top_rated"
    const val MOVIE_NOW_PLAYING = "/$API_VERSION/movie/now_playing"
    const val MOVIE_BY_ID = "/$API_VERSION/movie/{$MOVIE_ID_QUERY}"
}