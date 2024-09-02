package com.example.togetherapp.presentation.ui.details.coursedetailsscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.details.coursedetailsscreen.components.DetailsScreenContent
import com.example.togetherapp.presentation.viewmodel.details.CourseDetailsScreenViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    viewModel: CourseDetailsScreenViewModel,
    navController: NavHostController,
    courseId: String,
    courseIndex: Int) {
    DetailsScreenContent(viewModel, navController, courseId, courseIndex)
}