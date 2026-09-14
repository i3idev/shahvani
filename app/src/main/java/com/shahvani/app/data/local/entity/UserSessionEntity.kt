package com.shahvani.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_session")
data class UserSessionEntity(
    @PrimaryKey val id: Int = 0,
    val username: String,
    val email: String?,
    val avatar: String?,
    val isLoggedIn: Boolean,
    val lastSync: Long = System.currentTimeMillis()
)
