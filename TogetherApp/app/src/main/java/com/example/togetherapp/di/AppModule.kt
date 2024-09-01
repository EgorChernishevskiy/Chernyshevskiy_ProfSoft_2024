package com.example.togetherapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.togetherapp.data.database.NoteDatabase
import com.example.togetherapp.data.database.migration.MIGRATION_1_2
import com.example.togetherapp.data.database.migration.MIGRATION_2_3
import com.example.togetherapp.data.interceptor.TokenInterceptor
import com.example.togetherapp.data.mappers.auth.AuthMapper
import com.example.togetherapp.data.mappers.auth.AuthMapperImpl
import com.example.togetherapp.data.mappers.course.CourseMapper
import com.example.togetherapp.data.mappers.course.CourseMapperImpl
import com.example.togetherapp.data.mappers.favorite.FavoriteMapper
import com.example.togetherapp.data.mappers.favorite.FavoriteMapperImpl
import com.example.togetherapp.data.mappers.locnote.LocNoteMapper
import com.example.togetherapp.data.mappers.locnote.LocNoteMapperImpl
import com.example.togetherapp.data.mappers.note.NoteMapper
import com.example.togetherapp.data.mappers.note.NoteMapperImpl
import com.example.togetherapp.domain.repository.AuthRepository
import com.example.togetherapp.domain.repository.CourseRepository
import com.example.togetherapp.domain.repository.FavoriteRepository
import com.example.togetherapp.domain.repository.LocNoteRepository
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
import com.example.togetherapp.domain.usecase.comnote.AddCommentUseCase
import com.example.togetherapp.domain.usecase.comnote.CreateNoteUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNoteByIdUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNotesUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteCourseUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteLocalNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckCourseFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckLocalNoteFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckNoteFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteCourseUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteLocalNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteNoteUseCase
import com.example.togetherapp.domain.usecase.locnote.CreateLocalNoteUseCase
import com.example.togetherapp.domain.usecase.locnote.GetAllLocalNotesUseCase
import com.example.togetherapp.domain.usecase.locnote.GetLocalNoteByIdUseCase
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.CNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CourseDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CreateNoteViewModel
import com.example.togetherapp.presentation.viewmodel.LNoteDetailsScreenViewModel
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

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(),
            NoteDatabase::class.java,
            "note_database"
        )
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .allowMainThreadQueries()
            .build()
    }
    single { get<NoteDatabase>().noteDao() }
    single { get<NoteDatabase>().favoriteDao() }
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
    single<LocNoteRepository> {
        com.example.togetherapp.data.repository.LocNoteRepositoryImpl(
            get(), get()
        )
    }
    single<FavoriteRepository> {
        com.example.togetherapp.data.repository.FavoriteRepositoryImpl(
            get(), get()
        )
    }
}

val mapperModule = module {
    single<CourseMapper> { CourseMapperImpl() }
    single<NoteMapper> { NoteMapperImpl() }
    single<AuthMapper> { AuthMapperImpl() }
    single<LocNoteMapper> { LocNoteMapperImpl() }
    single<FavoriteMapper> { FavoriteMapperImpl() }
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
    single { GetNoteByIdUseCase(get()) }
    single { AddCommentUseCase(get()) }
    single { CreateNoteUseCase(get()) }

    single { GetAllLocalNotesUseCase(get()) }
    single { GetLocalNoteByIdUseCase(get()) }
    single { CreateLocalNoteUseCase(get()) }

    single { AddFavoriteCourseUseCase(get()) }
    single { RemoveFavoriteCourseUseCase(get()) }
    single { AddFavoriteNoteUseCase(get()) }
    single { RemoveFavoriteNoteUseCase(get()) }
    single { AddFavoriteLocalNoteUseCase(get()) }
    single { RemoveFavoriteLocalNoteUseCase(get()) }
    single { CheckCourseFavoriteStatusUseCase(get()) }
    single { CheckNoteFavoriteStatusUseCase(get()) }
    single { CheckLocalNoteFavoriteStatusUseCase(get()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { MainScreenViewModel(get(), get(), get()) }
    viewModel { CourseDetailsScreenViewModel(get(), get(), get(), get()) }
    viewModel { CNoteDetailsScreenViewModel(get(), get(), get(), get(), get()) }
    viewModel { LNoteDetailsScreenViewModel(get(), get(), get(), get()) }
    viewModel { CreateNoteViewModel(get(), get()) }
}

val appModules = listOf(
    networkModule,
    sharedPrefsModule,
    repositoryModule,
    mapperModule,
    useCaseModule,
    databaseModule,
    viewModelModule
)
