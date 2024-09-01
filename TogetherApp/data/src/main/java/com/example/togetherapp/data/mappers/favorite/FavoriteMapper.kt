package com.example.togetherapp.data.mappers.favorite

import com.example.togetherapp.data.database.entity.favorite.FavoriteCourseEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteLocalNoteEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteNoteEntity
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.locnote.LocNote

interface FavoriteMapper {
    fun mapToEntity(course: Course): FavoriteCourseEntity
    fun mapToEntity(note: Note): FavoriteNoteEntity
    fun mapToEntity(note: LocNote): FavoriteLocalNoteEntity
    fun mapFromEntity(entity: FavoriteCourseEntity): Course
    fun mapFromEntity(entity: FavoriteNoteEntity): Note
    fun mapFromEntity(entity: FavoriteLocalNoteEntity): LocNote
}