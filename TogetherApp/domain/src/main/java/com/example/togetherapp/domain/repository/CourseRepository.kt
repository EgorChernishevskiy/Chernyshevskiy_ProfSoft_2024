package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.model.course.ChallengeSubmissionDto
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.utils.NoteTopic

interface CourseRepository {
    suspend fun getChallenges(): List<ChallengeDto>
    suspend fun getCourseById(courseId: String): Course
    suspend fun createCourse(course: Course): Course
    suspend fun getChallengesByTopic(topic: NoteTopic): List<ChallengeDto>
    suspend fun getChallengeById(id: String): ChallengeDto
    suspend fun submitChallengeAttempt(
        challengeId: Long,
        image: String,
        text: String
    ): ChallengeSubmissionDto
}