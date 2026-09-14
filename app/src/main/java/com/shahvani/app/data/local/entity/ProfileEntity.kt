package com.shahvani.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey val id: Int,
    val username: String,
    val avatar: String?,
    val cover: String?,
    val bio: String?,
    val gender: String?,
    val location: String?,
    val website: String?,
    val createdAt: String?,
    val lastSeen: String?,
    val verified: Boolean,
    val online: Boolean,
    val blocked: Boolean,
    val cachedAt: Long = System.currentTimeMillis()
)
