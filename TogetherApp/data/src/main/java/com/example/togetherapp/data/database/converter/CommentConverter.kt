package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter
import com.example.togetherapp.domain.model.comnote.Comment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CommentConverter {

    @TypeConverter
    fun fromCommentList(commentList: List<Comment>): String {
        val gson = Gson()
        return gson.toJson(commentList)
    }

    @TypeConverter
    fun toCommentList(commentString: String): List<Comment> {
        val listType = object : TypeToken<List<Comment>>() {}.type
        return Gson().fromJson(commentString, listType)
    }
}