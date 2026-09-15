package com.shahvani.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadResponse(
    val success: Boolean,
    val url: String? = null,
    val id: String? = null,
    val message: String? = null,
    val error: String? = null
)

@Serializable
data class MediaResponse(
    val data: MediaData? = null,
    val message: String? = null,
    val error: String? = null
)

@Serializable
data class MediaData(
    val media: GalleryItemDto? = null,
    @SerialName("usage_bytes")
    val usageBytes: Long = 0,
    @SerialName("quota_bytes")
    val quotaBytes: Long = 0,
    val total: Int = 0
)
