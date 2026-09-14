package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.InboxDto
import com.shahvani.app.data.remote.dto.InboxSummaryDto
import com.shahvani.app.data.remote.dto.SendMessageRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MessageApi {
    
    @GET("/api/v1/messages/inbox/summary")
    suspend fun getInboxSummary(): Response<InboxSummaryDto>
    
    @GET("/api/v1/messages/inbox")
    suspend fun getInbox(@Query("limit") limit: Int? = null): Response<InboxDto>
    
    @POST("/api/v1/messages")
    suspend fun sendMessage(@Body request: SendMessageRequest): Response<Unit>
}
