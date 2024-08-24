package com.example.togetherapp.data.mappers

import com.example.togetherapp.data.course.model.CourseDto
import com.example.togetherapp.data.course.model.CourseTextDto
import com.example.togetherapp.domain.model.Course
import com.example.togetherapp.domain.model.CourseText

interface CourseMapper {
    fun toDomain(dto: CourseDto): Course
    fun toDto(domain: Course): CourseDto
    fun toDomain(dto: CourseTextDto): CourseText
    fun toDto(domain: CourseText): CourseTextDto
}