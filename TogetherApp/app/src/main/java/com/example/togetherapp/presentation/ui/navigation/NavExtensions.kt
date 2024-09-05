package com.example.togetherapp.presentation.ui.navigation

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.getStringArgument(key: String, defaultValue: String = ""): String {
    return this.arguments?.getString(key) ?: defaultValue
}

fun NavBackStackEntry.getIntArgument(key: String, defaultValue: Int = 0): Int {
    return this.arguments?.getInt(key) ?: defaultValue
}