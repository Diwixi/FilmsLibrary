package com.diwixis.filmlibrary.navigation

enum class Screens(
    private val params: List<String> = emptyList()
) {
    MoviesMainScreen,
    MoviesGreedScreen(
        params = listOf(ScreenConstants.TYPE)
    ),
    MovieDetailsScreen(
        params = listOf(ScreenConstants.ID)
    )
    ;

    val route: String
        get() = "${this.name}?${
            StringBuilder().also { builder ->
                params.forEach { builder.append("$it={$it}/") }
            }
        }"
}

object ScreenConstants {
    const val TYPE = "type"
    const val ID = "id"
}