package com.shahvani.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahvani.app.data.local.dao.NotificationDao
import com.shahvani.app.data.local.dao.ProfileDao
import com.shahvani.app.data.local.dao.TopicDao
import com.shahvani.app.data.local.dao.UserSessionDao
import com.shahvani.app.data.local.entity.NotificationEntity
import com.shahvani.app.data.local.entity.ProfileEntity
import com.shahvani.app.data.local.entity.TopicEntity
import com.shahvani.app.data.local.entity.UserSessionEntity

@Database(
    entities = [
        TopicEntity::class,
        ProfileEntity::class,
        UserSessionEntity::class,
        NotificationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShahvaniDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun profileDao(): ProfileDao
    abstract fun userSessionDao(): UserSessionDao
    abstract fun notificationDao(): NotificationDao
}
