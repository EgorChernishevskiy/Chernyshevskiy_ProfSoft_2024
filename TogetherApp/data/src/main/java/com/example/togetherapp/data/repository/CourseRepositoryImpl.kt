package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.CourseApi
import com.example.togetherapp.data.mappers.course.CourseMapper
import com.example.togetherapp.domain.model.course.ChallengeAttemptRequest
import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.model.course.ChallengeSubmissionDto
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.CourseRepository
import com.example.togetherapp.domain.utils.NoteTopic

class CourseRepositoryImpl(
    private val apiService: CourseApi,
    private val mapper: CourseMapper
) : CourseRepository {

    override suspend fun submitChallengeAttempt(
        challengeId: Long,
        image: String,
        text: String
    ): ChallengeSubmissionDto {
        val request = ChallengeAttemptRequest(challengeId, image, text)
        val response = apiService.submitChallengeAttempt(request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Ошибка при отправке попытки")
        } else {
            throw Exception("Ошибка при отправке попытки: ${response.message()}")
        }
    }

    override suspend fun getChallengeById(id: String): ChallengeDto {
        val response = apiService.getChallengeById(id)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Челлендж не найден")
        } else {
            throw Exception("Ошибка загрузки челленджа: ${response.message()}")
        }
    }

    override suspend fun getChallengesByTopic(topic: NoteTopic): List<ChallengeDto> {
        val response = apiService.getChallengesByTopic(topic.name)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Ошибка загрузки челленджей по теме")
        }
    }

    override suspend fun getChallenges(): List<ChallengeDto> {
        val response = apiService.getChallenges()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Ошибка загрузки челленджей: ${response.message()}")
        }
    }

    override suspend fun getCourseById(courseId: String): Course {
        val response = apiService.getCourseById(courseId)
        if (response.isSuccessful) {
            return response.body()?.data?.let { mapper.toDomain(it) }
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