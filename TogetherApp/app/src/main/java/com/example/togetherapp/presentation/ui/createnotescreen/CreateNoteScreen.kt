package com.example.togetherapp.presentation.ui.createnotescreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.createnotescreen.components.CreateNoteScreenContent
import com.example.togetherapp.presentation.viewmodel.CreateNoteViewModel

@Composable
fun CreateNoteScreen(
    viewModel: CreateNoteViewModel,
    navController: NavHostController
) {
    CreateNoteScreenContent(viewModel, navController)
}