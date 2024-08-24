package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.Course
import com.example.togetherapp.domain.repository.CourseRepository

class GetCourseByIdUseCase(private val repository: CourseRepository) {
    suspend fun execute(courseId: String): Course {
        return repository.getCourseById(courseId)
    }
}