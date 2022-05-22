package com.diwixis.filmlibrary.features.favourites

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diwixis.filmlibrary.navigation.FlNavigationDestination

object FavouritesDestination : FlNavigationDestination {
    override val route = "favourites_route"
    override val destination = "favourites_destination"
}

fun NavGraphBuilder.favouritesGraph(
    onBackClick: () -> Unit
) {
    composable(route = FavouritesDestination.route) { navBackStackEntry ->
        Text("FavouritesDestination")
    }
}