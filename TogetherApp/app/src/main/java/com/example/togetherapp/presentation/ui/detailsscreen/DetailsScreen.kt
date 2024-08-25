package com.example.togetherapp.presentation.ui.detailsscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.detailsscreen.components.DetailsScreenContent
import com.example.togetherapp.presentation.viewmodel.DetailsScreenViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    viewModel: DetailsScreenViewModel,
    navController: NavHostController,
    courseId: String,
    courseIndex: Int) {
    DetailsScreenContent(viewModel, navController, courseId, courseIndex)
}