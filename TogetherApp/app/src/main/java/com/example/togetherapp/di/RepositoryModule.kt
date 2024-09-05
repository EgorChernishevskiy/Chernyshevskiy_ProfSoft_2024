package com.example.togetherapp.di

import com.example.togetherapp.domain.repository.AuthRepository
import com.example.togetherapp.domain.repository.ChatRepository
import com.example.togetherapp.domain.repository.CourseRepository
import com.example.togetherapp.domain.repository.FavoriteRepository
import com.example.togetherapp.domain.repository.LocNoteRepository
import com.example.togetherapp.domain.repository.NoteRepository
import com.example.togetherapp.domain.repository.TokenRepository
import com.example.togetherapp.domain.repository.UserProfileRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> {
        com.example.togetherapp.data.repository.AuthRepositoryImpl(
            get(), get()
        )
    }
    factory<TokenRepository> {
        com.example.togetherapp.data.repository.TokenRepositoryImpl(
            get()
        )
    }
    factory<CourseRepository> {
        com.example.togetherapp.data.repository.CourseRepositoryImpl(
            get(), get()
        )
    }
    factory<NoteRepository> {
        com.example.togetherapp.data.repository.NoteRepositoryImpl(
            get(), get()
        )
    }
    factory<LocNoteRepository> {
        com.example.togetherapp.data.repository.LocNoteRepositoryImpl(
            get(), get()
        )
    }
    factory<FavoriteRepository> {
        com.example.togetherapp.data.repository.FavoriteRepositoryImpl(
            get(), get()
        )
    }
    factory<ChatRepository> {
        com.example.togetherapp.data.repository.ChatRepositoryImpl(
            get(), get()
        )
    }
    factory<UserProfileRepository> {
        com.example.togetherapp.data.repository.UserProfileRepositoryImpl(
            get(), get()
        )
    }
}