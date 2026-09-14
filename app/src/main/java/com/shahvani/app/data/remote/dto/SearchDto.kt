package com.shahvani.app.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchUsersResponse(
    val users: List<UserDto>,
    val total: Int,
    val limit: Int,
    val query: String
)
