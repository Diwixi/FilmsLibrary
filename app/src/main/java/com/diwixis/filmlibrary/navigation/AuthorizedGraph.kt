package com.diwixis.filmlibrary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diwixis.filmlibrary.navigation.Screens
import com.diwixis.filmlibrary.presentation.MoviesGreedScreen
import com.diwixis.filmlibrary.presentation.MoviesMainScreen

@Composable
fun AuthorizedGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MoviesMainScreen.route) {
        composable(Screens.MoviesMainScreen.route) {
            MoviesMainScreen()//onOpenGreedScreen = {}, onOpenDetailsScreen = {})
        }
        composable(Screens.MoviesGreedScreen.route) {
            MoviesGreedScreen()
        }
        composable(Screens.MovieDetailsScreen.route) {
            //TODO open details
        }
    }
}