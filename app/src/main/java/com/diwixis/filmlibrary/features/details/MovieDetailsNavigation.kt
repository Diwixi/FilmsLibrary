package com.diwixis.filmlibrary.features.details

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.diwixis.filmlibrary.navigation.FlNavigationDestination
import com.diwixis.filmlibrary.navigation.getString

object DetailsDestination : FlNavigationDestination {
    override val route = "details_route"
    override val destination = "details_destination"
    const val movieIdArg = "movieId"
}

fun NavGraphBuilder.detailsGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = "${DetailsDestination.route}/{${DetailsDestination.movieIdArg}}",
        arguments = listOf(
            navArgument(DetailsDestination.movieIdArg) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        navBackStackEntry.getString(DetailsDestination.movieIdArg)?.let { movieId ->
            MovieDetailsRoute(
                onBackClick = onBackClick,
                movieId = movieId.toInt()
            )
        }
    }
}