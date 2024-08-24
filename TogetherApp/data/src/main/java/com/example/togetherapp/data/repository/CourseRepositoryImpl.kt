package com.example.togetherapp.data.repository

import com.example.togetherapp.data.course.CourseNetwork
import com.example.togetherapp.data.mappers.CourseMapper
import com.example.togetherapp.domain.model.Course
import com.example.togetherapp.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val apiRepository: CourseNetwork,
    private val mapper: CourseMapper
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        val response = apiRepository.getCourses()
        if (response.isSuccessful) {
            return response.body()?.data?.map { mapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch courses: ${response.message()}")
        }
    }

    override suspend fun getCourseById(courseId: String): Course {
        val response = apiRepository.getCourseById(courseId)
        if (response.isSuccessful) {
            return response.body()?.let { mapper.toDomain(it) }
                ?: throw Exception("Course not found")
        } else {
            throw Exception("Failed to fetch course: ${response.message()}")
        }
    }

    override suspend fun createCourse(course: Course): Course {
        val courseDto = mapper.toDto(course)
        val response = apiRepository.createCourse(courseDto)
        if (response.isSuccessful) {
            return response.body()?.let { mapper.toDomain(it) }
                ?: throw Exception("Failed to create course")
        } else {
            throw Exception("Failed to create course: ${response.message()}")
        }
    }
}