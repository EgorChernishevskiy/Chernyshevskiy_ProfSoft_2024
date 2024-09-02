package com.example.togetherapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
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
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModel()
    private val splashScreenViewModel: SplashScreenViewModel by viewModel()
    private val mainScreenViewModel: MainScreenViewModel by viewModel()
    private val courseDetailsScreenViewModel: CourseDetailsScreenViewModel by viewModel()
    private val cNoteDetailsScreenViewModel: CNoteDetailsScreenViewModel by viewModel()
    private val lNoteDetailsScreenViewModel: LNoteDetailsScreenViewModel by viewModel()
    private val createNoteScreenViewModel: CreateNoteScreenViewModel by viewModel()
    private val favoriteScreenViewModel: FavoriteScreenViewModel by viewModel()
    private val chatScreenViewModel: ChatScreenViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TogetherAppTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                    splashScreenViewModel = splashScreenViewModel,
                    mainScreenViewModel = mainScreenViewModel,
                    courseDetailsScreenViewModel = courseDetailsScreenViewModel,
                    cNoteDetailsScreenViewModel = cNoteDetailsScreenViewModel,
                    lNoteDetailsScreenViewModel =lNoteDetailsScreenViewModel,
                    createNoteScreenViewModel = createNoteScreenViewModel,
                    favoriteScreenViewModel = favoriteScreenViewModel,
                    chatScreenViewModel = chatScreenViewModel
                )
            }
        }
    }
}