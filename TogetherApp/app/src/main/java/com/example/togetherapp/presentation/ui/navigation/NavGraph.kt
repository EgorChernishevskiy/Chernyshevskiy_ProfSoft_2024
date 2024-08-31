package com.example.togetherapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.togetherapp.presentation.ui.details.coursedetailsscreen.DetailScreen
import com.example.togetherapp.presentation.ui.auth.loginscreen.LoginScreen
import com.example.togetherapp.presentation.ui.mainscreen.MainScreen
import com.example.togetherapp.presentation.ui.auth.registerscreen.RegisterScreen
import com.example.togetherapp.presentation.ui.createnotescreen.CreateNoteScreen
import com.example.togetherapp.presentation.ui.details.cnotedetailsscreen.CNoteDetailScreen
import com.example.togetherapp.presentation.ui.details.lnotesdetails.LNoteDetailsScreen
import com.example.togetherapp.presentation.ui.splashscreen.SplashScreen
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.CNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CourseDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CreateNoteViewModel
import com.example.togetherapp.presentation.viewmodel.LNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    splashScreenViewModel: SplashScreenViewModel,
    mainScreenViewModel: MainScreenViewModel,
    courseDetailsScreenViewModel: CourseDetailsScreenViewModel,
    cNoteDetailsScreenViewModel: CNoteDetailsScreenViewModel,
    lNoteDetailsScreenViewModel: LNoteDetailsScreenViewModel,
    createNoteViewModel: CreateNoteViewModel
) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                navController = navController,
                viewModel = splashScreenViewModel
            )
        }
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                navController = navController
            )
        }
        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                navController = navController
            )
        }
        composable("home") {
            MainScreen(
                viewModel = mainScreenViewModel,
                navController = navController
            )
        }
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
                viewModel = courseDetailsScreenViewModel,
                navController = navController,
                courseId = courseId,
                courseIndex = courseIndex
            )
        }
        composable(
            "cnote/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
            CNoteDetailScreen(
                viewModel = cNoteDetailsScreenViewModel,
                navController = navController,
                noteId = noteId
            )
        }
        composable(
            "lnote/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
            LNoteDetailsScreen(
                viewModel = lNoteDetailsScreenViewModel,
                navController = navController,
                noteId = noteId
            )
        }
        composable("createnote") {
            CreateNoteScreen(
                viewModel = createNoteViewModel,
                navController = navController
            )
        }
    }
}