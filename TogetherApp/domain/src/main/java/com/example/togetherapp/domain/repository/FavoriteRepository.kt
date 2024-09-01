package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.locnote.LocNote

interface FavoriteRepository {
    suspend fun addFavoriteCourse(course: Course)
    suspend fun addFavoriteNote(note: Note)
    suspend fun addFavoriteLocalNote(note: LocNote)
    suspend fun getAllFavoriteCourses(): List<Course>
    suspend fun getAllFavoriteLocalNotes(): List<LocNote>
    suspend fun getAllFavoriteComNotes(): List<Note>
    suspend fun removeFavoriteCourse(courseId: String)
    suspend fun removeFavoriteNote(noteId: String)
    suspend fun removeFavoriteLocalNote(noteId: Int)
    suspend fun isCourseFavorite(courseId: String): Boolean
    suspend fun isNoteFavorite(noteId: String): Boolean
    suspend fun isLocalNoteFavorite(noteId: Int): Boolean
}