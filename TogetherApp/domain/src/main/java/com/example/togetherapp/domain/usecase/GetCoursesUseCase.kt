package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.Course
import com.example.togetherapp.domain.repository.CourseRepository

class GetCoursesUseCase(private val repository: CourseRepository) {
    suspend fun execute(): List<Course> {
        return repository.getCourses()
    }
}