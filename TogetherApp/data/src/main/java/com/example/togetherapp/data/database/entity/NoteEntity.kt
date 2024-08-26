package com.example.togetherapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.utils.NoteContentConverter

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    @TypeConverters(NoteContentConverter::class)
    val content: List<NoteContentEntity>,
    val date: String
)