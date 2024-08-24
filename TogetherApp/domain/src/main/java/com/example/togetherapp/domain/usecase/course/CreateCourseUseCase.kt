package com.example.togetherapp.domain.usecase.course

import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.CourseRepository

class CreateCourseUseCase(private val repository: CourseRepository) {
    suspend fun execute(course: Course): Course {
        return repository.createCourse(course)
    }
}