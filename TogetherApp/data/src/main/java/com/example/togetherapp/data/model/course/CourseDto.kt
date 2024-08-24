package com.example.togetherapp.data.model.course

data class CourseDto(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val text: List<CourseTextDto>
)