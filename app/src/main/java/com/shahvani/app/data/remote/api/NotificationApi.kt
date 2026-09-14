package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.NotificationSummaryDto
import com.shahvani.app.data.remote.dto.NotificationsResponseDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationApi {
    
    @GET("/api/v1/notifications/summary")
    suspend fun getSummary(): Response<NotificationSummaryDto>
    
    @GET("/api/v1/notifications")
    suspend fun getNotifications(@Query("limit") limit: Int = 25): Response<NotificationsResponseDto>
    
    @DELETE("/api/v1/notifications")
    suspend fun clearAll(): Response<Unit>
    
    @DELETE("/api/v1/notifications/{id}")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}
