package com.example.togetherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.togetherapp.data.database.entity.favorite.FavoriteCourseEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteNoteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteCourse(course: FavoriteCourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteNote(note: FavoriteNoteEntity)

    @Query("SELECT * FROM favorite_courses")
    fun getAllFavoriteCourses(): List<FavoriteCourseEntity>

    @Query("SELECT * FROM favorite_notes")
    fun getAllFavoriteNotes(): List<FavoriteNoteEntity>

    @Query("DELETE FROM favorite_courses WHERE id = :courseId")
    fun removeFavoriteCourse(courseId: String)

    @Query("DELETE FROM favorite_notes WHERE id = :noteId")
    fun removeFavoriteNote(noteId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_courses WHERE id = :courseId)")
    fun isCourseFavorite(courseId: String): Boolean
}
