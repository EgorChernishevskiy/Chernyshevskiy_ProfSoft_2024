package com.example.togetherapp.data.mappers.course

import com.example.togetherapp.data.model.course.CourseDto
import com.example.togetherapp.data.model.course.CourseTextDto
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.course.CourseText

interface CourseMapper {
    fun toDomain(dto: CourseDto): Course
    fun toDto(domain: Course): CourseDto
    fun toDomain(dto: CourseTextDto): CourseText
    fun toDto(domain: CourseText): CourseTextDto
}