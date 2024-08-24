package com.example.togetherapp.data.course.networkcourse

import com.example.togetherapp.data.api.CourseApi
import com.example.togetherapp.data.course.model.CourseDto
import com.example.togetherapp.data.model.CourseResponse
import retrofit2.Response

class CourseNetworkImpl(
    private val apiService: CourseApi
) : com.example.togetherapp.data.course.CourseNetwork {

    override suspend fun getCourses(): Response<CourseResponse> {
        return apiService.getCourses()
    }

    override suspend fun getCourseById(courseId: String): Response<CourseDto> {
        return apiService.getCourseById(courseId)
    }

    override suspend fun createCourse(course: CourseDto): Response<CourseDto> {
        return apiService.createCourse(course)
    }
}