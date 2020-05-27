package com.diwixis.filmlibrary.presentation.movieList

import com.diwixis.filmlibrary.presentation.Movie

/**
 * Created by Diwixis on 19.04.2017.
 */
interface MovieGreedView {
    fun showLoad()
    fun hideLoad()
    fun showMovie(movieList: List<Movie>)
}