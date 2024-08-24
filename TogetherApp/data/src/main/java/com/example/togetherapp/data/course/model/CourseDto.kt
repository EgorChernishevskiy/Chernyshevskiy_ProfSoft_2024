package com.example.togetherapp.data.course.model

data class CourseDto(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val text: List<CourseTextDto>
)