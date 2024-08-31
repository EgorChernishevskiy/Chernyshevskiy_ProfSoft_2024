package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter
import com.example.togetherapp.data.database.entity.note.NoteContentEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocNoteContentConverter {

    @TypeConverter
    fun fromNoteContentList(content: List<NoteContentEntity>): String {
        return Gson().toJson(content)
    }

    @TypeConverter
    fun toNoteContentList(contentString: String): List<NoteContentEntity> {
        val listType = object : TypeToken<List<NoteContentEntity>>() {}.type
        return Gson().fromJson(contentString, listType)
    }
}