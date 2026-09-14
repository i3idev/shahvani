package com.shahvani.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class TopicEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val slug: String,
    val content: String,
    val authorUsername: String,
    val authorAvatar: String?,
    val forumId: Int,
    val createdAt: String,
    val updatedAt: String?,
    val views: Int,
    val likes: Int,
    val replies: Int,
    val pinned: Boolean,
    val locked: Boolean,
    val bookmarked: Boolean
)
