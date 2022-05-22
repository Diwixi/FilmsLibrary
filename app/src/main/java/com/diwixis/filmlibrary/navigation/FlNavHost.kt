package com.diwixis.filmlibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.diwixis.filmlibrary.features.details.DetailsDestination
import com.diwixis.filmlibrary.features.details.detailsGraph
import com.diwixis.filmlibrary.features.favourites.favouritesGraph
import com.diwixis.filmlibrary.features.list.ListDestination
import com.diwixis.filmlibrary.features.list.listGraph
import com.diwixis.filmlibrary.features.movies.MoviesPreviewDestination
import com.diwixis.filmlibrary.features.movies.moviesPreviewGraph
import com.diwixis.filmlibrary.features.settings.settingsGraph

@Composable
fun FlNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MoviesPreviewDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        moviesPreviewGraph(
            navigateToDetails = { navController.navigate("${DetailsDestination.route}/$it") },
            navigateToList = { navController.navigate("${ListDestination.route}/$it") }
        )
        detailsGraph(
            onBackClick = { navController.popBackStack() }
        )
        listGraph(
            onBackClick = { navController.popBackStack() }
        )
        favouritesGraph(
            onBackClick = { navController.popBackStack() }
        )
        settingsGraph(
            onBackClick = { navController.popBackStack() }
        )
    }
}