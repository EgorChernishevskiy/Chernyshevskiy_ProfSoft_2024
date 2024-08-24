package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.Course
import com.example.togetherapp.domain.repository.CourseRepository

class CreateCourseUseCase(private val repository: CourseRepository) {
    suspend fun execute(course: Course): Course {
        return repository.createCourse(course)
    }
}