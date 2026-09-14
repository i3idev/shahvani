package com.shahvani.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: Int,
    val username: String,
    val avatar: String? = null,
    val cover: String? = null,
    val bio: String? = null,
    val gender: String? = null,
    val location: String? = null,
    val website: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("last_seen")
    val lastSeen: String? = null,
    val verified: Boolean = false,
    val online: Boolean = false,
    val blocked: Boolean = false
)

@Serializable
data class ProfileStatsDto(
    val posts: Int = 0,
    val topics: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val likes: Int = 0,
    val photos: Int = 0,
    val stories: Int = 0
)

@Serializable
data class ProfileContentDto(
    val items: List<ContentItemDto>,
    val total: Int,
    val limit: Int,
    val offset: Int
)

@Serializable
data class ContentItemDto(
    val id: Int,
    val type: String,
    val title: String? = null,
    val content: String? = null,
    val media: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    val likes: Int = 0,
    val views: Int = 0,
    val author: UserDto? = null
)

@Serializable
data class GalleryDto(
    val items: List<GalleryItemDto>,
    val total: Int,
    val page: Int,
    val limit: Int
)

@Serializable
data class GalleryItemDto(
    val id: Int,
    val url: String,
    val thumbnail: String? = null,
    val type: String,
    @SerialName("created_at")
    val createdAt: String
)
