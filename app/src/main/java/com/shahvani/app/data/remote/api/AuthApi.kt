package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.AuthRequest
import com.shahvani.app.data.remote.dto.AuthResponse
import com.shahvani.app.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/v1/auth")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>

    @GET("/api/v1/auth/me")
    suspend fun getCurrentUser(): Response<UserDto>

    @POST("/api/v1/auth/logout")
    suspend fun logout(): Response<Unit>
}
