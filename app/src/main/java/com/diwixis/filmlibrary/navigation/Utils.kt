package com.diwixis.filmlibrary.navigation

import androidx.navigation.NavBackStackEntry
import java.io.Serializable

fun String.replaceParam(key: String, value: String): String =
    this.replace("={$key}", "=$value")

fun NavBackStackEntry.getSerializable(key: String): Serializable? =
    this.arguments?.getSerializable(key)

fun NavBackStackEntry.getInt(key: String): Int? =
    this.arguments?.getInt(key)

fun NavBackStackEntry.getString(key: String): String? =
    this.arguments?.getString(key)