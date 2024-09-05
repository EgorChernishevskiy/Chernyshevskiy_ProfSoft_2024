package com.example.togetherapp.presentation.ui.details.coursedetailsscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.details.coursedetailsscreen.components.DetailsScreenContent


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    navController: NavHostController,
    courseId: String,
    courseIndex: Int) {
    DetailsScreenContent(navController, courseId, courseIndex)
}