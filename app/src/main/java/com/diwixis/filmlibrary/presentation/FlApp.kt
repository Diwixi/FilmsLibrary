package com.diwixis.filmlibrary.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diwixis.filmlibrary.navigation.FlBottomBar
import com.diwixis.filmlibrary.navigation.FlNavHost
import com.diwixis.filmlibrary.navigation.FlNavRail
import com.diwixis.filmlibrary.navigation.FlTopLevelNavigation
import com.diwixis.filmlibrary.ui.theme.FlTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FlApp(windowSizeClass: WindowSizeClass) {
    FlTheme {
        val navController = rememberNavController()
        val topLevelNavigation = remember(navController) {
            FlTopLevelNavigation(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val contentPadding = WindowInsets.systemBars.asPaddingValues()

        Scaffold(
            modifier = Modifier.padding(contentPadding),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                    FlBottomBar(
                        onNavigateToTopLevelDestination = topLevelNavigation::navigateTo,
                        currentDestination = currentDestination
                    )
                }
            }
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                if (windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
                    FlNavRail(
                        onNavigateToTopLevelDestination = topLevelNavigation::navigateTo,
                        currentDestination = currentDestination
                    )
                }

                FlNavHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )
            }
        }
    }
}