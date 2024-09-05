package com.example.togetherapp.di

import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.ChatScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CreateNoteScreenViewModel
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel
import com.example.togetherapp.presentation.viewmodel.ProfileScreenViewModel
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel
import com.example.togetherapp.presentation.viewmodel.details.CNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.details.CourseDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.details.LNoteDetailsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { MainScreenViewModel(get(), get(), get()) }
    viewModel { CourseDetailsScreenViewModel(get(), get(), get(), get()) }
    viewModel { CNoteDetailsScreenViewModel(get(), get(), get(), get(), get()) }
    viewModel { LNoteDetailsScreenViewModel(get(), get(), get(), get()) }
    viewModel { CreateNoteScreenViewModel(get(), get()) }
    viewModel { FavoriteScreenViewModel(get(), get(), get()) }
    viewModel { ChatScreenViewModel(get(), get(), get()) }
    viewModel { ProfileScreenViewModel(get(), get(), get(), get(), get()) }
}