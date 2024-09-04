package com.example.togetherapp.presentation.ui.profilescreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.favoritescreen.components.FavoriteScreenContent
import com.example.togetherapp.presentation.ui.profilescreen.components.ProfileScreenContent
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel
import com.example.togetherapp.presentation.viewmodel.ProfileScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(navController: NavHostController) {
    ProfileScreenContent(navController)
}