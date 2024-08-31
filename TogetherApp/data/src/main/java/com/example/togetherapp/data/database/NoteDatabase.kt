package com.example.togetherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.converter.AuthorConverter
import com.example.togetherapp.data.database.converter.CommentConverter
import com.example.togetherapp.data.database.converter.CourseTextConverter
import com.example.togetherapp.data.database.converter.LocNoteContentConverter
import com.example.togetherapp.data.database.converter.NoteContentConverter
import com.example.togetherapp.data.database.dao.FavoriteDao
import com.example.togetherapp.data.database.dao.NoteDao
import com.example.togetherapp.data.database.entity.favorite.FavoriteCourseEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteNoteEntity
import com.example.togetherapp.data.database.entity.note.NoteEntity
import com.example.togetherapp.data.database.converter.TagsConverter

@Database(
    entities = [NoteEntity::class, FavoriteCourseEntity::class, FavoriteNoteEntity::class],
    version = 2
)
@TypeConverters(
    NoteContentConverter::class,
    LocNoteContentConverter::class,
    CourseTextConverter::class,
    TagsConverter::class,
    AuthorConverter::class,
    CommentConverter::class
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun favoriteDao(): FavoriteDao
}