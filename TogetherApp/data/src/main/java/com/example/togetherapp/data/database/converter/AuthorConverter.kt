package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter
import com.example.togetherapp.domain.model.comnote.Author
import com.google.gson.Gson

class AuthorConverter {

    @TypeConverter
    fun fromAuthor(author: Author): String {
        return Gson().toJson(author)
    }

    @TypeConverter
    fun toAuthor(authorString: String): Author {
        return Gson().fromJson(authorString, Author::class.java)
    }
}