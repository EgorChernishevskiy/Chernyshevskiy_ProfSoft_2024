package com.example.togetherapp.presentation.ui.details.lnotesdetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.details.lnotesdetails.components.LNoteDetailsScreenContent
import com.example.togetherapp.presentation.viewmodel.details.LNoteDetailsScreenViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LNoteDetailsScreen(
    viewModel: LNoteDetailsScreenViewModel,
    navController: NavHostController,
    noteId: Int
) {
    LNoteDetailsScreenContent(viewModel, navController, noteId)
}