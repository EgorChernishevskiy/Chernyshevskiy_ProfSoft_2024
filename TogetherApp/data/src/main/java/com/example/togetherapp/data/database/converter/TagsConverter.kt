package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter

class TagsConverter {

    @TypeConverter
    fun fromTagsList(tags: List<String>): String {
        return tags.joinToString(separator = ",")
    }

    @TypeConverter
    fun toTagsList(tagsString: String): List<String> {
        return tagsString.split(",").map { it.trim() }
    }
}