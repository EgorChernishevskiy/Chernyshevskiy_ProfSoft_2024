package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NoteContentConverter {

    @TypeConverter
    fun fromNoteContentList(contentList: List<NoteContent>): String {
        val gson = Gson()
        return gson.toJson(contentList)
    }

    @TypeConverter
    fun toNoteContentList(contentString: String): List<NoteContent> {
        val listType = object : TypeToken<List<NoteContent>>() {}.type
        return Gson().fromJson(contentString, listType)
    }
}