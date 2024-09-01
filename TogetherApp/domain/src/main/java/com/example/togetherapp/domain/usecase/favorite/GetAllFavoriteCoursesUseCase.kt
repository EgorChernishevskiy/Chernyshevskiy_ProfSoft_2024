package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.FavoriteRepository

class GetAllFavoriteCoursesUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(): List<Course> {
        return repository.getAllFavoriteCourses()
    }
}