package com.example.togetherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.dao.NoteDao
import com.example.togetherapp.data.database.entity.NoteEntity
import com.example.togetherapp.data.database.utils.NoteContentConverter

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(NoteContentConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}