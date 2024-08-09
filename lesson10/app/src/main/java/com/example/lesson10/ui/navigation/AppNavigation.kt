package com.example.lesson10.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lesson10.ui.mainscreen.MainScreen
import com.example.lesson10.ui.profileeditscreen.EditProfileScreen
import com.example.lesson10.ui.profilescreen.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { MainScreen(navController) }
        composable("profile/{name}/{surname}/{middleName}/{birthdate}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Иван"
            val surname = backStackEntry.arguments?.getString("surname") ?: "Иванов"
            val middleName = backStackEntry.arguments?.getString("middleName") ?: "Иванович"
            val birthdate = backStackEntry.arguments?.getString("birthdate") ?: "01.01.2001"
            ProfileScreen(navController, name, surname, middleName, birthdate)
        }
        composable("editProfile/{name}/{surname}/{middleName}/{birthdate}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Иван"
            val surname = backStackEntry.arguments?.getString("surname") ?: "Иванов"
            val middleName = backStackEntry.arguments?.getString("middleName") ?: "Иванович"
            val birthdate = backStackEntry.arguments?.getString("birthdate") ?: "01.01.2001"
            EditProfileScreen(navController, name, surname, middleName, birthdate)
        }
    }
}

