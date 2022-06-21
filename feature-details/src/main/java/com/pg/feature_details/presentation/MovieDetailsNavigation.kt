package com.pg.feature_details.presentation

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
        route = "${com.pg.feature_details.DetailsDestination.route}/{${com.pg.feature_details.DetailsDestination.movieIdArg}}",
        arguments = listOf(
            navArgument(com.pg.feature_details.DetailsDestination.movieIdArg) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        navBackStackEntry.getString(com.pg.feature_details.DetailsDestination.movieIdArg)?.let { movieId ->
            MovieDetailsRoute(
                onBackClick = onBackClick,
                movieId = movieId.toInt()
            )
        }
    }
}