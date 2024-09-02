package com.example.togetherapp.presentation.ui.createnotescreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.createnotescreen.components.CreateNoteScreenContent
import com.example.togetherapp.presentation.viewmodel.CreateNoteScreenViewModel

@Composable
fun CreateNoteScreen(
    viewModel: CreateNoteScreenViewModel,
    navController: NavHostController
) {
    CreateNoteScreenContent(viewModel, navController)
}