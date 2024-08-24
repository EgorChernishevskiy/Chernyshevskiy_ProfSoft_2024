package com.example.togetherapp.data.course

import com.example.togetherapp.data.course.model.CourseDto
import retrofit2.Response

interface CourseNetwork {
    suspend fun getCourses(): Response<List<CourseDto>>
    suspend fun getCourseById(courseId: String): Response<CourseDto>
    suspend fun createCourse(course: CourseDto): Response<CourseDto>
}