package com.example.togetherapp.data.database.entity.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.converter.NoteContentConverter
import com.example.togetherapp.domain.model.comnote.NoteContent

@Entity(tableName = "favorite_local_notes")
data class FavoriteLocalNoteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    @TypeConverters(NoteContentConverter::class) val content: List<NoteContent>,
    val date: String,
)