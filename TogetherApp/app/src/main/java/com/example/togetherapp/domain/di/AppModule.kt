package com.example.togetherapp.domain.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.auth.UserAuth
import com.example.togetherapp.data.auth.password.PasswordUserAuth
import com.example.togetherapp.data.repository.AuthRepositoryImpl
import com.example.togetherapp.data.repository.TokenRepositoryImplementation
import com.example.togetherapp.domain.repository.AuthRepository
import com.example.togetherapp.domain.repository.TokenRepository
import com.example.togetherapp.domain.usecase.CheckTokenUseCase
import com.example.togetherapp.domain.usecase.LoginUseCase
import com.example.togetherapp.domain.usecase.RegisterUseCase
import com.example.togetherapp.domain.usecase.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.ValidateFirstNameUseCase
import com.example.togetherapp.domain.usecase.ValidateLastNameUseCase
import com.example.togetherapp.domain.usecase.ValidatePasswordUseCase
import com.example.togetherapp.domain.usecase.ValidatePhoneNumberUseCase
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("http://profsoft.ddns.net:8080/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}

val sharedPrefsModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
}

val repositoryModule = module {
    single<UserAuth> { PasswordUserAuth(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<TokenRepository> { TokenRepositoryImplementation(get()) }
}

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { RegisterUseCase(get()) }
    single { CheckTokenUseCase(get()) }
    single { SaveTokenUseCase(get()) }
    single { ValidateFirstNameUseCase() }
    single { ValidateLastNameUseCase() }
    single { ValidatePhoneNumberUseCase() }
    single { ValidatePasswordUseCase() }

}

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get() ) }
    viewModel { SplashScreenViewModel(get()) }
}

val appModules = listOf(networkModule, sharedPrefsModule, repositoryModule, useCaseModule, viewModelModule)
