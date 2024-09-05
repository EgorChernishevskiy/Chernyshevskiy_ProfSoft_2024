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

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = Routes.Splash, modifier = modifier) {
        composable(Routes.Splash) {
            SplashScreen(navController = navController)
        }
        composable(Routes.Login) {
            LoginScreen(navController = navController)
        }
        composable(Routes.Register) {
            RegisterScreen(navController = navController)
        }
        composable(Routes.Home) {
            MainScreen(navController = navController)
        }
        composable(
            Routes.Details,
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType },
                navArgument("courseIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.getStringArgument("courseId")
            val courseIndex = backStackEntry.getIntArgument("courseIndex")
            DetailScreen(navController = navController, courseId = courseId, courseIndex = courseIndex)
        }
        composable(
            Routes.CNoteDetail,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.getStringArgument("noteId")
            CNoteDetailScreen(navController = navController, noteId = noteId)
        }
        composable(
            Routes.LNoteDetail,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.getIntArgument("noteId")
            LNoteDetailsScreen(navController = navController, noteId = noteId)
        }
        composable(Routes.CreateNote) {
            CreateNoteScreen(navController = navController)
        }
        composable(Routes.Favorite) {
            FavoriteScreen(navController = navController)
        }
        composable(Routes.Chat) {
            ChatScreen()
        }
        composable(Routes.Profile) {
            ProfileScreen(navController = navController)
        }
    }
}
