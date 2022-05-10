package com.diwixis.filmlibrary.navigation

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.diwixis.filmlibrary.presentation.MovieDetailsScreen
import com.diwixis.filmlibrary.presentation.MovieListScreen
import com.diwixis.filmlibrary.presentation.MoviesMainScreen

@Composable
fun AuthorizedGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(

            ) {
                // TODO: Add BottomBar
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screens.MoviesMainScreen.route) {
            composable(Screens.MoviesMainScreen.route) {
                MoviesMainScreen(
                    onOpenGreedScreen = {
                        navController.navigate(
                            Screens.MoviesGreedScreen.routeWithArgs(
                                name = ScreenConstants.TYPE,
                                param = it.name
                            )
                        )
                    },
                    onOpenDetailsScreen = {
                        navController.navigate(
                            Screens.MovieDetailsScreen.routeWithArgs(
                                name = ScreenConstants.ID,
                                param = it.toString()
                            )
                        )
                    }
                )
            }
            composable(
                route = Screens.MoviesGreedScreen.route,
                arguments = listOf(
                    navArgument(ScreenConstants.TYPE) {
                        type = NavType.EnumType(type = GreedType::class.java)
                    }
                )
            ) { navBackStack ->
                val type = (navBackStack.getSerializable(ScreenConstants.TYPE) as GreedType)
                MovieListScreen(GreedType.values().find { it.name == type.name })
            }
            composable(
                route = Screens.MovieDetailsScreen.route,
                arguments = listOf(
                    navArgument(ScreenConstants.ID) { type = NavType.IntType }
                )
            ) { navBackStack ->
                val movieId = navBackStack.getInt(ScreenConstants.ID)
                MovieDetailsScreen(movieId)
            }
        }
    }
}