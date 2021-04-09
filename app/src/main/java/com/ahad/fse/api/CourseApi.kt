package com.ahad.fse.api

import com.ahad.fse.models.Course
import com.ahad.fse.models.CourseDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseApi {

    @GET("course/{courseId}")
    suspend fun getCourse(@Path("courseId")id: Long): Response<CourseDetail>

    @GET("course/user/{username}")
    suspend fun getCoursesForUser(@Path("username")username: String): Response<List<Course>>
}