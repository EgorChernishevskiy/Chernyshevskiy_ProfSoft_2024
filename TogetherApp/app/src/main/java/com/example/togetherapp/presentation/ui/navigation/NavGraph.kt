package com.example.togetherapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.togetherapp.presentation.ui.detailsscreen.DetailScreen
import com.example.togetherapp.presentation.ui.loginscreen.LoginScreen
import com.example.togetherapp.presentation.ui.mainscreen.MainScreen
import com.example.togetherapp.presentation.ui.registerscreen.RegisterScreen
import com.example.togetherapp.presentation.ui.splashscreen.SplashScreen
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.DetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    splashScreenViewModel: SplashScreenViewModel,
    mainScreenViewModel: MainScreenViewModel,
    detailsScreenViewModel: DetailsScreenViewModel
) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController = navController, viewModel = splashScreenViewModel) }
        composable("login") { LoginScreen(viewModel = authViewModel, navController = navController) }
        composable("register") { RegisterScreen(viewModel = authViewModel, navController = navController) }
        composable("home") { MainScreen(viewModel = mainScreenViewModel, navController = navController) }
        composable(
            "details/{courseId}/{courseIndex}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType },
                navArgument("courseIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            val courseIndex = backStackEntry.arguments?.getInt("courseIndex") ?: 0
            DetailScreen(
                viewModel = detailsScreenViewModel,
                navController = navController,
                courseId = courseId,
                courseIndex = courseIndex
            )
        }
    }
}