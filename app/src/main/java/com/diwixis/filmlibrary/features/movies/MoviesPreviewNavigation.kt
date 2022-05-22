package com.diwixis.filmlibrary.features.movies

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diwixis.filmlibrary.navigation.FlNavigationDestination

object MoviesPreviewDestination : FlNavigationDestination {
    override val route = "movies_preview"
    override val destination = "for_you_destination"
}

fun NavGraphBuilder.moviesPreviewGraph(
    navigateToDetails: (String) -> Unit,
    navigateToList: (String) -> Unit
) {
    composable(route = MoviesPreviewDestination.route) {
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