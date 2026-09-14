package com.shahvani.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    val id: Int,
    val type: String,
    val title: String,
    val message: String,
    val data: NotificationDataDto? = null,
    @SerialName("created_at")
    val createdAt: String,
    val read: Boolean = false
)

@Serializable
data class NotificationDataDto(
    val userId: Int? = null,
    val username: String? = null,
    val topicId: Int? = null,
    val postId: Int? = null,
    val url: String? = null
)

@Serializable
data class NotificationSummaryDto(
    val total: Int,
    val unread: Int
)

@Serializable
data class NotificationsResponseDto(
    val notifications: List<NotificationDto>,
    val total: Int,
    val limit: Int
)
