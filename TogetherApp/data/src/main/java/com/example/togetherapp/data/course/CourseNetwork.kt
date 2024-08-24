package com.example.togetherapp.data.course

import com.example.togetherapp.data.course.model.CourseDto
import com.example.togetherapp.data.model.CourseResponse
import retrofit2.Response

interface CourseNetwork {
    suspend fun getCourses(): Response<CourseResponse>
    suspend fun getCourseById(courseId: String): Response<CourseDto>
    suspend fun createCourse(course: CourseDto): Response<CourseDto>
}