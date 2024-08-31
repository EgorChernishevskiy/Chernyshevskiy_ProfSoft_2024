package com.example.togetherapp.data.repository

import com.example.togetherapp.data.database.dao.FavoriteDao
import com.example.togetherapp.data.mappers.favorite.FavoriteMapper
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao,
    private val favoriteMapper: FavoriteMapper
) : FavoriteRepository {

    override suspend fun addFavoriteCourse(course: Course) {
        val entity = favoriteMapper.mapToEntity(course)
        favoriteDao.addFavoriteCourse(entity)
    }

    override suspend fun addFavoriteNote(note: Note) {
        val entity = favoriteMapper.mapToEntity(note)
        favoriteDao.addFavoriteNote(entity)
    }

    override suspend fun getAllFavorites(): Pair<List<Course>, List<Note>> {
        val favoriteCourses = favoriteDao.getAllFavoriteCourses().map { favoriteMapper.mapFromEntity(it) }
        val favoriteNotes = favoriteDao.getAllFavoriteNotes().map { favoriteMapper.mapFromEntity(it) }
        return Pair(favoriteCourses, favoriteNotes)
    }

    override suspend fun removeFavoriteCourse(courseId: String) {
        favoriteDao.removeFavoriteCourse(courseId)
    }

    override suspend fun removeFavoriteNote(noteId: String) {
        favoriteDao.removeFavoriteNote(noteId)
    }

    override suspend fun isCourseFavorite(courseId: String): Boolean {
        return favoriteDao.isCourseFavorite(courseId)
    }
}