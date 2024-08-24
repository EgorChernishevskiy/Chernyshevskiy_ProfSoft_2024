package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.course.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun getCourseById(courseId: String): Course
    suspend fun createCourse(course: Course): Course
}