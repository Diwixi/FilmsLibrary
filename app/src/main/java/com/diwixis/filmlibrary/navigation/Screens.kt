package com.diwixis.filmlibrary.navigation

enum class Screens {
    MoviesMainScreen,
    MoviesGreedScreen,
    MovieDetailsScreen
    ;

    val route: String
        get() = this.name
}