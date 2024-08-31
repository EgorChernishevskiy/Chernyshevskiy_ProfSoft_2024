package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course

interface FavoriteRepository {
    suspend fun addFavoriteCourse(course: Course)
    suspend fun addFavoriteNote(note: Note)
    suspend fun getAllFavorites(): Pair<List<Course>, List<Note>>
    suspend fun removeFavoriteCourse(courseId: String)
    suspend fun removeFavoriteNote(noteId: String)
    suspend fun isCourseFavorite(courseId: String): Boolean
}