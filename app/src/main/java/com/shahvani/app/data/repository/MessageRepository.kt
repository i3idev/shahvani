package com.shahvani.app.data.repository

import com.shahvani.app.core.network.CsrfTokenManager
import com.shahvani.app.data.remote.api.MessageApi
import com.shahvani.app.data.remote.dto.InboxDto
import com.shahvani.app.data.remote.dto.InboxSummaryDto
import com.shahvani.app.data.remote.dto.SendMessageRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val messageApi: MessageApi,
    private val csrfTokenManager: CsrfTokenManager
) {
    suspend fun getInboxSummary(): Result<InboxSummaryDto> {
        return try {
            val response = messageApi.getInboxSummary()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load inbox summary"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getInbox(limit: Int? = null): Result<InboxDto> {
        return try {
            val response = messageApi.getInbox(limit)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load inbox"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun sendMessage(recipient: String, subject: String, content: String): Result<Unit> {
        return withCsrfToken {
            val response = messageApi.sendMessage(SendMessageRequest(recipient, subject, content))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to send message"))
            }
        }
    }
    
    private suspend fun <T> withCsrfToken(action: suspend () -> Result<T>): Result<T> {
        val csrfResult = csrfTokenManager.getOrFetchToken()
        return if (csrfResult.isSuccess) {
            action()
        } else {
            Result.failure(csrfResult.exceptionOrNull() ?: Exception("CSRF token fetch failed"))
        }
    }
}
