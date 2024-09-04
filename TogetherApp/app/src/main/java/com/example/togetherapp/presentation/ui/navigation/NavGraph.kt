package com.example.togetherapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.togetherapp.presentation.ui.details.coursedetailsscreen.DetailScreen
import com.example.togetherapp.presentation.ui.auth.loginscreen.LoginScreen
import com.example.togetherapp.presentation.ui.mainscreen.MainScreen
import com.example.togetherapp.presentation.ui.auth.registerscreen.RegisterScreen
import com.example.togetherapp.presentation.ui.chatscreen.ChatScreen
import com.example.togetherapp.presentation.ui.createnotescreen.CreateNoteScreen
import com.example.togetherapp.presentation.ui.details.cnotedetailsscreen.CNoteDetailScreen
import com.example.togetherapp.presentation.ui.details.lnotesdetails.LNoteDetailsScreen
import com.example.togetherapp.presentation.ui.favoritescreen.FavoriteScreen
import com.example.togetherapp.presentation.ui.profilescreen.ProfileScreen
import com.example.togetherapp.presentation.ui.splashscreen.SplashScreen
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.ChatScreenViewModel
import com.example.togetherapp.presentation.viewmodel.details.CNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.details.CourseDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CreateNoteScreenViewModel
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel
import com.example.togetherapp.presentation.viewmodel.details.LNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel
import com.example.togetherapp.presentation.viewmodel.ProfileScreenViewModel
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = "splash", modifier = modifier) {
        composable("splash") {
            SplashScreen(
                navController = navController
            )
        }
        composable("login") {
            LoginScreen(
                navController = navController
            )
        }
        composable("register") {
            RegisterScreen(
                navController = navController
            )
        }
        composable("home") {
            MainScreen(
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
                navController = navController,
                noteId = noteId
            )
        }
        composable(
            "lnote/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            LNoteDetailsScreen(
                navController = navController,
                noteId = noteId
            )
        }
        composable("createnote") {
            CreateNoteScreen(
                navController = navController
            )
        }
        composable("favorite") {
            FavoriteScreen(
                navController = navController
            )
        }
        composable("chat") {
            ChatScreen()
        }
        composable("profile") {
            ProfileScreen(
                navController = navController
            )
        }
    }
}