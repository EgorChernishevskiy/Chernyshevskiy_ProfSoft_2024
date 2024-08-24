package com.example.togetherapp.domain.model

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val text: List<CourseText>
)