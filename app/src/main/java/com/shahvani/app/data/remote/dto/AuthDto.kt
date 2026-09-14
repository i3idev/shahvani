package com.shahvani.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String
)

@Serializable
data class UserDto(
    val id: Int,
    val username: String,
    val email: String? = null,
    val avatar: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null
)

@Serializable
data class AuthResponse(
    val user: UserDto? = null,
    val message: String? = null,
    val error: String? = null
)
