package com.diwixis.filmlibrary.movies_module

import com.diwixis.filmlibrary.data.MoviesBean

/**
 * Created by Diwixis on 19.04.2017.
 */
interface MovieGreedView {
    fun showLoad()
    fun hideLoad()
    fun showMovie(movieList: List<Movie>)
}