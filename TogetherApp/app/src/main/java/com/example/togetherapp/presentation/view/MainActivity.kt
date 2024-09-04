package com.example.togetherapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.togetherapp.presentation.ui.components.BottomNavigationBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.togetherapp.presentation.ui.navigation.NavGraph
import com.example.togetherapp.presentation.ui.theme.TogetherAppTheme
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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TogetherAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        if (currentRoute in listOf("home", "favorite", "chat", "profile")) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}