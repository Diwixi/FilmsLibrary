package com.diwixis.filmlibrary.features.settings

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diwixis.filmlibrary.navigation.FlNavigationDestination

object SettingsDestination : FlNavigationDestination {
    override val route = "settings_route"
    override val destination = "settings_destination"
}

fun NavGraphBuilder.settingsGraph(
    onBackClick: () -> Unit
) {
    composable(route = SettingsDestination.route) { navBackStackEntry ->
        Text("SettingsDestination")
    }
}