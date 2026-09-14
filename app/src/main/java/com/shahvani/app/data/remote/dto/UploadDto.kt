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
    val success: Boolean,
    val media: GalleryItemDto? = null,
    val message: String? = null,
    val error: String? = null
)
