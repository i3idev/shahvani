package com.shahvani.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey val id: Int,
    val type: String,
    val title: String,
    val message: String,
    val createdAt: String,
    val read: Boolean,
    val cachedAt: Long = System.currentTimeMillis()
)
