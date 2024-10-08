package com.example.togetherapp.presentation.ui.details.cnotedetailsscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.details.cnotedetailsscreen.components.CNoteDetailsScreenContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CNoteDetailScreen(
    navController: NavHostController,
    noteId: String
) {
    CNoteDetailsScreenContent(navController, noteId)
}