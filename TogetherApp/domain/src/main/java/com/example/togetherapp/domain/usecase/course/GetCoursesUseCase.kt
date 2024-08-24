package com.example.togetherapp.domain.usecase.course

import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.CourseRepository

class GetCoursesUseCase(private val repository: CourseRepository) {
    suspend fun execute(): List<Course> {
        return repository.getCourses()
    }
}