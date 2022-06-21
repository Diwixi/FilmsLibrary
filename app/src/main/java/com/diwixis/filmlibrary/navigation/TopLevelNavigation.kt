package com.diwixis.filmlibrary.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.features.favourites.FavouritesDestination
import com.pg.feature_preview.MoviesPreviewDestination
import com.diwixis.filmlibrary.features.settings.SettingsDestination

class FlTopLevelNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

data class TopLevelDestination(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int
) {
    fun icon(selected: Boolean): Int = if (selected) selectedIcon else unselectedIcon
}

val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = com.pg.feature_preview.MoviesPreviewDestination.route,
        selectedIcon = NavIcons.MoviesSelected,
        unselectedIcon = NavIcons.MoviesSelected,
        iconTextId = R.string.preview_screen
    ),
    TopLevelDestination(
        route = FavouritesDestination.route,
        selectedIcon = NavIcons.FavouritesSelected,
        unselectedIcon = NavIcons.Favourites,
        iconTextId = R.string.favourites
    ),
    TopLevelDestination(
        route = SettingsDestination.route,
        selectedIcon = NavIcons.SettingsSelected,
        unselectedIcon = NavIcons.Settings,
        iconTextId = R.string.settings
    )
)