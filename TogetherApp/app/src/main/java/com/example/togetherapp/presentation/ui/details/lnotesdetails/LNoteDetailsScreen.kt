package com.example.togetherapp.presentation.ui.details.lnotesdetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.details.lnotesdetails.components.LNoteDetailsScreenContent


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LNoteDetailsScreen(
    navController: NavHostController,
    noteId: Int
) {
    LNoteDetailsScreenContent(navController, noteId)
}