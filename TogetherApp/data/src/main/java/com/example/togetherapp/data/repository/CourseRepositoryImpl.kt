package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.CourseApi
import com.example.togetherapp.data.mappers.course.CourseMapper
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val apiService: CourseApi,
    private val mapper: CourseMapper
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        val response = apiService.getCourses()
        if (response.isSuccessful) {
            return response.body()?.data?.map { mapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch courses: ${response.message()}")
        }
    }

    override suspend fun getCourseById(courseId: String): Course {
        val response = apiService.getCourseById(courseId)
        if (response.isSuccessful) {
            return response.body()?.let { mapper.toDomain(it) }
                ?: throw Exception("Course not found")
        } else {
            throw Exception("Failed to fetch course: ${response.message()}")
        }
    }

    override suspend fun createCourse(course: Course): Course {
        val courseDto = mapper.toDto(course)
        val response = apiService.createCourse(courseDto)
        if (response.isSuccessful) {
            return response.body()?.let { mapper.toDomain(it) }
                ?: throw Exception("Failed to create course")
        } else {
            throw Exception("Failed to create course: ${response.message()}")
        }
    }
}