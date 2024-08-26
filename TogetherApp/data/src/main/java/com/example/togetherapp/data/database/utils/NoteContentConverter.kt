package com.example.togetherapp.data.database.utils

import androidx.room.TypeConverter
import com.example.togetherapp.data.database.entity.NoteContentEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NoteContentConverter {

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