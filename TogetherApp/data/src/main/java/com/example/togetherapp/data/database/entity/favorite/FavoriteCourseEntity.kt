package com.example.togetherapp.data.database.entity.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.converter.CourseTextConverter
import com.example.togetherapp.data.database.converter.TagsConverter
import com.example.togetherapp.domain.model.course.CourseText

@Entity(tableName = "favorite_courses")
data class FavoriteCourseEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    @TypeConverters(TagsConverter::class) val tags: List<String>,
    @TypeConverters(CourseTextConverter::class) val text: List<CourseText>
)