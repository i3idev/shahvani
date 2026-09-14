package com.shahvani.app.data.repository

import com.shahvani.app.data.local.dao.NotificationDao
import com.shahvani.app.data.local.entity.NotificationEntity
import com.shahvani.app.data.remote.api.NotificationApi
import com.shahvani.app.data.remote.dto.NotificationDto
import com.shahvani.app.data.remote.dto.NotificationSummaryDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor(
    private val notificationApi: NotificationApi,
    private val notificationDao: NotificationDao
) {
    val notificationsFlow: Flow<List<NotificationEntity>> = notificationDao.getAllNotifications()
    val unreadCountFlow: Flow<Int> = notificationDao.getUnreadCount()
    
    suspend fun getSummary(): Result<NotificationSummaryDto> {
        return try {
            val response = notificationApi.getSummary()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load notification summary"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getNotifications(limit: Int = 25, forceRefresh: Boolean = false): Result<List<NotificationEntity>> {
        return try {
            val response = notificationApi.getNotifications(limit)
            if (response.isSuccessful && response.body() != null) {
                val entities = response.body()!!.notifications.map { it.toEntity() }
                notificationDao.insertNotifications(entities)
                Result.success(entities)
            } else {
                Result.failure(Exception("Failed to load notifications"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun clearAll(): Result<Unit> {
        return try {
            val response = notificationApi.clearAll()
            if (response.isSuccessful) {
                notificationDao.clearAll()
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to clear notifications"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteNotification(id: Int): Result<Unit> {
        return try {
            val response = notificationApi.delete(id)
            if (response.isSuccessful) {
                notificationDao.deleteById(id)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete notification"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun markAsRead(id: Int): Result<Unit> {
        return try {
            notificationDao.markAsRead(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun NotificationDto.toEntity() = NotificationEntity(
        id = id,
        type = type,
        title = title,
        message = message,
        createdAt = createdAt,
        read = read
    )
}
