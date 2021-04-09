package com.ahad.fse.api

import com.ahad.fse.models.CourseDetail
import com.ahad.fse.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("login/")
    suspend fun getCourse(@Body user: User): Response<User>

    @POST("register/")
    suspend fun register(@Body user: User): Response<User>
}