package com.diwixis.filmlibrary.features.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.diwixis.filmlibrary.features.list.ListDestination.listTypeArg
import com.diwixis.filmlibrary.navigation.FlNavigationDestination
import com.diwixis.filmlibrary.navigation.ListType
import com.diwixis.filmlibrary.navigation.getString

object ListDestination : FlNavigationDestination {
    override val route = "list_route"
    override val destination = "list_destination"
    const val listTypeArg = "list_type"
}

fun NavGraphBuilder.listGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = "${ListDestination.route}/{${listTypeArg}}",
        arguments = listOf(
            navArgument(listTypeArg) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        navBackStackEntry.getString(listTypeArg)?.let {
            MovieListRoute(onBackClick = onBackClick, type = ListType.valueOf(it))
        }
    }
}