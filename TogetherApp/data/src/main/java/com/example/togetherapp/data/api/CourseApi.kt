package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.course.CourseDto
import com.example.togetherapp.data.model.course.IdCourseResponse
import com.example.togetherapp.data.model.note.NoteDto
import com.example.togetherapp.domain.model.course.ChallengeAttemptRequest
import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.model.course.ChallengeSubmissionDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseApi {
    @GET("/challenge/get_all")
    suspend fun getChallenges(): Response<List<ChallengeDto>>

    @POST("/challenge/submit")
    suspend fun submitChallengeAttempt(
        @Body attempt: ChallengeAttemptRequest
    ): Response<ChallengeSubmissionDto>

    @GET("challenge/get_by_technique")
    suspend fun getChallengesByTopic(@Query("technique") topic: String): Response<List<ChallengeDto>>

    @GET("/challenge/get")
    suspend fun getChallengeById(@Query("id") id: String): Response<ChallengeDto>

    @GET("/api/courses/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: String): Response<IdCourseResponse>

    @POST("/api/courses")
    suspend fun createCourse(@Body course: CourseDto): Response<CourseDto>
}