package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter
import com.example.togetherapp.domain.model.course.CourseText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CourseTextConverter {

    @TypeConverter
    fun fromCourseTextList(textList: List<CourseText>): String {
        val gson = Gson()
        return gson.toJson(textList)
    }

    @TypeConverter
    fun toCourseTextList(textString: String): List<CourseText> {
        val listType = object : TypeToken<List<CourseText>>() {}.type
        return Gson().fromJson(textString, listType)
    }
}