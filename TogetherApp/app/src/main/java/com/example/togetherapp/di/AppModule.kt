package com.example.togetherapp.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.togetherapp.data.interceptor.TokenInterceptor
import com.example.togetherapp.data.mappers.auth.AuthMapper
import com.example.togetherapp.data.mappers.auth.AuthMapperImpl
import com.example.togetherapp.data.mappers.course.CourseMapper
import com.example.togetherapp.data.mappers.course.CourseMapperImpl
import com.example.togetherapp.data.mappers.note.NoteMapper
import com.example.togetherapp.data.mappers.note.NoteMapperImpl
import com.example.togetherapp.domain.repository.AuthRepository
import com.example.togetherapp.domain.repository.CourseRepository
import com.example.togetherapp.domain.repository.NoteRepository
import com.example.togetherapp.domain.repository.TokenRepository
import com.example.togetherapp.domain.usecase.course.GetCoursesUseCase
import com.example.togetherapp.domain.usecase.auth.CheckTokenUseCase
import com.example.togetherapp.domain.usecase.auth.LoginUseCase
import com.example.togetherapp.domain.usecase.auth.RegisterUseCase
import com.example.togetherapp.domain.usecase.auth.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.auth.ValidateFirstNameUseCase
import com.example.togetherapp.domain.usecase.auth.ValidateLastNameUseCase
import com.example.togetherapp.domain.usecase.auth.ValidatePasswordUseCase
import com.example.togetherapp.domain.usecase.auth.ValidatePhoneNumberUseCase
import com.example.togetherapp.domain.usecase.course.GetCourseByIdUseCase
import com.example.togetherapp.domain.usecase.note.GetNotesUseCase
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.DetailsScreenViewModel
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
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.NoteApi::class.java)
    }
}

val sharedPrefsModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
}

val repositoryModule = module {
    single<AuthRepository> {
        com.example.togetherapp.data.repository.AuthRepositoryImpl(
            get(), get()
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
    single<NoteRepository> {
        com.example.togetherapp.data.repository.NoteRepositoryImpl(
            get(), get()
        )
    }
}

val mapperModule = module {
    single<CourseMapper> { CourseMapperImpl() }
    single<NoteMapper> { NoteMapperImpl() }
    single<AuthMapper> { AuthMapperImpl() }
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
    single { GetCourseByIdUseCase(get()) }
    single { GetNotesUseCase(get()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { DetailsScreenViewModel(get()) }
}

val appModules = listOf(
    networkModule,
    sharedPrefsModule,
    repositoryModule,
    mapperModule,
    useCaseModule,
    viewModelModule
)
