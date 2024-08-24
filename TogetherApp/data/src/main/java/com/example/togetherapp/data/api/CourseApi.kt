package com.example.togetherapp.data.api

import com.example.togetherapp.data.course.model.CourseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CourseApi {
    @GET("/api/courses")
    suspend fun getCourses(): Response<List<CourseDto>>

    @GET("/api/courses/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: String): Response<CourseDto>

    @POST("/api/courses")
    suspend fun createCourse(@Body course: CourseDto): Response<CourseDto>
}