package com.example.togetherapp.di

import com.example.togetherapp.domain.usecase.auth.CheckTokenUseCase
import com.example.togetherapp.domain.usecase.auth.LoginUseCase
import com.example.togetherapp.domain.usecase.auth.RegisterUseCase
import com.example.togetherapp.domain.usecase.auth.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.auth.ValidateFirstNameUseCase
import com.example.togetherapp.domain.usecase.auth.ValidateLastNameUseCase
import com.example.togetherapp.domain.usecase.auth.ValidatePasswordUseCase
import com.example.togetherapp.domain.usecase.auth.ValidatePhoneNumberUseCase
import com.example.togetherapp.domain.usecase.chat.GetAllMessagesUseCase
import com.example.togetherapp.domain.usecase.chat.SendMessageUseCase
import com.example.togetherapp.domain.usecase.comnote.AddCommentUseCase
import com.example.togetherapp.domain.usecase.comnote.CreateNoteUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNoteByIdUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNotesUseCase
import com.example.togetherapp.domain.usecase.course.GetCourseByIdUseCase
import com.example.togetherapp.domain.usecase.course.GetCoursesUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteCourseUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteLocalNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckCourseFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckLocalNoteFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckNoteFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.GetAllFavoriteComNotesUseCase
import com.example.togetherapp.domain.usecase.favorite.GetAllFavoriteCoursesUseCase
import com.example.togetherapp.domain.usecase.favorite.GetAllFavoriteLocalNotesUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteCourseUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteLocalNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteNoteUseCase
import com.example.togetherapp.domain.usecase.locnote.CreateLocalNoteUseCase
import com.example.togetherapp.domain.usecase.locnote.GetAllLocalNotesUseCase
import com.example.togetherapp.domain.usecase.locnote.GetLocalNoteByIdUseCase
import com.example.togetherapp.domain.usecase.profile.GetAllUserProfilesUseCase
import com.example.togetherapp.domain.usecase.profile.GetUserProfileByIdUseCase
import com.example.togetherapp.domain.usecase.profile.GetUserProfileUseCase
import com.example.togetherapp.domain.usecase.profile.LogOutUseCase
import com.example.togetherapp.domain.usecase.profile.SetPhoneVisibilityUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }

    factory { CheckTokenUseCase(get()) }
    factory{ SaveTokenUseCase(get()) }

    factory { ValidateFirstNameUseCase() }
    factory { ValidateLastNameUseCase() }
    factory { ValidatePhoneNumberUseCase() }
    factory { ValidatePasswordUseCase() }

    factory { GetCoursesUseCase(get()) }
    factory { GetCourseByIdUseCase(get()) }

    factory { GetNotesUseCase(get()) }
    factory { GetNoteByIdUseCase(get()) }
    factory { AddCommentUseCase(get()) }
    factory { CreateNoteUseCase(get()) }

    factory { GetAllLocalNotesUseCase(get()) }
    factory { GetLocalNoteByIdUseCase(get()) }
    factory { CreateLocalNoteUseCase(get()) }

    factory { AddFavoriteCourseUseCase(get()) }
    factory { RemoveFavoriteCourseUseCase(get()) }
    factory { AddFavoriteNoteUseCase(get()) }
    factory { RemoveFavoriteNoteUseCase(get()) }
    factory { AddFavoriteLocalNoteUseCase(get()) }
    factory { RemoveFavoriteLocalNoteUseCase(get()) }
    factory { CheckCourseFavoriteStatusUseCase(get()) }
    factory { CheckNoteFavoriteStatusUseCase(get()) }
    factory { CheckLocalNoteFavoriteStatusUseCase(get()) }
    factory { GetAllFavoriteCoursesUseCase(get()) }
    factory { GetAllFavoriteLocalNotesUseCase(get()) }
    factory { GetAllFavoriteComNotesUseCase(get()) }

    factory { GetAllMessagesUseCase(get()) }
    factory { SendMessageUseCase(get()) }

    factory { GetAllUserProfilesUseCase(get()) }
    factory { GetUserProfileByIdUseCase(get()) }
    factory { GetUserProfileUseCase(get()) }
    factory { SetPhoneVisibilityUseCase(get()) }
    factory { LogOutUseCase(get()) }
}