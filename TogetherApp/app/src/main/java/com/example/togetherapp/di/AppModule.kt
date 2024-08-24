package com.example.togetherapp.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.togetherapp.data.interceptor.TokenInterceptor
import com.example.togetherapp.data.mappers.CourseMapper
import com.example.togetherapp.data.mappers.CourseMapperImpl
import com.example.togetherapp.domain.repository.AuthRepository
import com.example.togetherapp.domain.repository.CourseRepository
import com.example.togetherapp.domain.repository.TokenRepository
import com.example.togetherapp.domain.usecase.GetCoursesUseCase
import com.example.togetherapp.domain.usecase.auth.CheckTokenUseCase
import com.example.togetherapp.domain.usecase.auth.LoginUseCase
import com.example.togetherapp.domain.usecase.auth.RegisterUseCase
import com.example.togetherapp.domain.usecase.auth.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.auth.ValidateFirstNameUseCase
import com.example.togetherapp.domain.usecase.auth.ValidateLastNameUseCase
import com.example.togetherapp.domain.usecase.auth.ValidatePasswordUseCase
import com.example.togetherapp.domain.usecase.auth.ValidatePhoneNumberUseCase
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val tokenRepository: TokenRepository = get()

        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(tokenRepository))
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("http://profsoft.ddns.net:8080/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.AuthApi::class.java)
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.CourseApi::class.java)
    }
}

val sharedPrefsModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
}

val repositoryModule = module {
    single<com.example.togetherapp.data.auth.UserAuth> {
        com.example.togetherapp.data.auth.password.PasswordUserAuth(
            get()
        )
    }
    single<AuthRepository> {
        com.example.togetherapp.data.repository.AuthRepositoryImpl(
            get()
        )
    }
    single<TokenRepository> {
        com.example.togetherapp.data.repository.TokenRepositoryImplementation(
            get()
        )
    }
    single<CourseRepository> {
        com.example.togetherapp.data.repository.CourseRepositoryImpl(
            get(), get()
        )
    }
    single<com.example.togetherapp.data.course.CourseNetwork> {
        com.example.togetherapp.data.course.networkcourse.CourseNetworkImpl(
            get()
        )
    }
}

val mapperModule = module {
    single<CourseMapper> { CourseMapperImpl() }
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
    single { GetCoursesUseCase(get()) }

}

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get() ) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { MainScreenViewModel(get()) }
}

val appModules = listOf(
    networkModule,
    sharedPrefsModule,
    repositoryModule,
    mapperModule,
    useCaseModule,
    viewModelModule
)
