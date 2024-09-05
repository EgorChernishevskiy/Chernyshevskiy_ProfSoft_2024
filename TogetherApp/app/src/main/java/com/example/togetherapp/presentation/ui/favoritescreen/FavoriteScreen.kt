package com.example.togetherapp.presentation.ui.favoritescreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.favoritescreen.components.FavoriteScreenContent


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteScreen(navController: NavHostController) {
    FavoriteScreenContent(navController)
}