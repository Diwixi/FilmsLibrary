package com.diwixis.filmlibrary.navigation

import androidx.navigation.NavBackStackEntry
import java.io.Serializable

fun Screens.routeWithArgs(name: String, param: String): String {
    return this.route.replaceParam(key = name, value = param)
}

fun Screens.routeWithArgs(params: List<Pair<String, String>>): String {
    var newRoute = this.route
    params.forEach {
        newRoute = newRoute.replaceParam(key = it.first, value = it.second)
    }
    return newRoute
}

fun <T : Enum<T>> Screens.routeWithArgs(enumType: T): String =
    this.route.replaceParam(key = enumType::class.java.simpleName, value = enumType.name)

fun String.replaceParam(key: String, value: String): String =
    this.replace("={$key}", "=$value")

fun NavBackStackEntry.getSerializable(key: String): Serializable? =
    this.arguments?.getSerializable(key)

fun NavBackStackEntry.getInt(key: String): Int? =
    this.arguments?.getInt(key)