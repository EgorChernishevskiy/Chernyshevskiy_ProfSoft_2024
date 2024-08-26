package com.example.togetherapp.presentation.ui.cnotedetailsscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.cnotedetailsscreen.components.CNoteDetailsScreenContent
import com.example.togetherapp.presentation.viewmodel.CNoteDetailsScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CNoteDetailScreen(
    viewModel: CNoteDetailsScreenViewModel,
    navController: NavHostController,
    noteId: String
) {
    CNoteDetailsScreenContent(viewModel, navController, noteId)
}