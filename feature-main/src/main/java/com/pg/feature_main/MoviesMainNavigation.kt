package com.pg.feature_main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diwixis.filmlibrary.navigation.FlNavigationDestination

object MoviesMainDestination : FlNavigationDestination {
    override val route = "movies_preview"
    override val destination = "for_you_destination"
}

fun NavGraphBuilder.moviesMainGraph(
    navigateToDetails: (String) -> Unit,
    navigateToList: (String) -> Unit
) {
    composable(route = MoviesMainDestination.route) {
        MoviesPreviewRoute(
            onBackClick = {},
            onOpenListScreen = {
                navigateToList(it.name)
            },
            onOpenDetailsScreen = {
                navigateToDetails(it.toString())
            }
        )
    }
}