package com.example.togetherapp.di

import com.example.togetherapp.data.mappers.auth.AuthMapper
import com.example.togetherapp.data.mappers.auth.AuthMapperImpl
import com.example.togetherapp.data.mappers.chat.ChatMapper
import com.example.togetherapp.data.mappers.chat.ChatMapperImpl
import com.example.togetherapp.data.mappers.course.CourseMapper
import com.example.togetherapp.data.mappers.course.CourseMapperImpl
import com.example.togetherapp.data.mappers.favorite.FavoriteMapper
import com.example.togetherapp.data.mappers.favorite.FavoriteMapperImpl
import com.example.togetherapp.data.mappers.locnote.LocNoteMapper
import com.example.togetherapp.data.mappers.locnote.LocNoteMapperImpl
import com.example.togetherapp.data.mappers.note.NoteMapper
import com.example.togetherapp.data.mappers.note.NoteMapperImpl
import com.example.togetherapp.data.mappers.profile.UserProfileMapper
import com.example.togetherapp.data.mappers.profile.UserProfileMapperImpl
import org.koin.dsl.module

val mapperModule = module {
    single<CourseMapper> { CourseMapperImpl() }
    single<NoteMapper> { NoteMapperImpl() }
    single<AuthMapper> { AuthMapperImpl() }
    single<LocNoteMapper> { LocNoteMapperImpl() }
    single<FavoriteMapper> { FavoriteMapperImpl() }
    single<ChatMapper> { ChatMapperImpl() }
    single<UserProfileMapper> { UserProfileMapperImpl() }
}