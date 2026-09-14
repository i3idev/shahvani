package com.shahvani.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: Int,
    val sender: UserDto,
    val recipient: UserDto? = null,
    val subject: String? = null,
    val content: String,
    @SerialName("created_at")
    val createdAt: String,
    val read: Boolean = false
)

@Serializable
data class InboxSummaryDto(
    val total: Int,
    val unread: Int
)

@Serializable
data class InboxDto(
    val messages: List<MessageDto>,
    val total: Int,
    val limit: Int,
    val offset: Int
)

@Serializable
data class SendMessageRequest(
    val recipient: String,
    val subject: String,
    val content: String
)
