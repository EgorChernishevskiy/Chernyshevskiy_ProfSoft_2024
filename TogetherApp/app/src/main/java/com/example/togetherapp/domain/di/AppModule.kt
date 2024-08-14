package com.example.togetherapp.domain.di

import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.repository.AuthRepositoryImpl
import com.example.togetherapp.domain.repository.AuthRepository
import com.example.togetherapp.domain.usecase.LoginUseCase
import com.example.togetherapp.domain.usecase.RegisterUseCase
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://profsoft.ddns.net:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(AuthApi::class.java)
    }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get()) }
}

val appModules = listOf(networkModule, repositoryModule, useCaseModule, viewModelModule)
