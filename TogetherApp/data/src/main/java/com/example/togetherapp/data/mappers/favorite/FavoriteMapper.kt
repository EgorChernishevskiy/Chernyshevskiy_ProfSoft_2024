package com.example.togetherapp.data.mappers.favorite

import com.example.togetherapp.data.database.entity.favorite.FavoriteCourseEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteNoteEntity
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course

interface FavoriteMapper {
    fun mapToEntity(course: Course): FavoriteCourseEntity
    fun mapToEntity(note: Note): FavoriteNoteEntity
    fun mapFromEntity(entity: FavoriteCourseEntity): Course
    fun mapFromEntity(entity: FavoriteNoteEntity): Note
}