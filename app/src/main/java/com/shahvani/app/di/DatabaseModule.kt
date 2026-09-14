package com.shahvani.app.di

import android.content.Context
import androidx.room.Room
import com.shahvani.app.data.local.ShahvaniDatabase
import com.shahvani.app.data.local.dao.NotificationDao
import com.shahvani.app.data.local.dao.ProfileDao
import com.shahvani.app.data.local.dao.TopicDao
import com.shahvani.app.data.local.dao.UserSessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ShahvaniDatabase {
        return Room.databaseBuilder(
            context,
            ShahvaniDatabase::class.java,
            "shahvani_db"
        ).build()
    }
    
    @Provides
    fun provideTopicDao(database: ShahvaniDatabase): TopicDao = database.topicDao()
    
    @Provides
    fun provideProfileDao(database: ShahvaniDatabase): ProfileDao = database.profileDao()
    
    @Provides
    fun provideUserSessionDao(database: ShahvaniDatabase): UserSessionDao = database.userSessionDao()
    
    @Provides
    fun provideNotificationDao(database: ShahvaniDatabase): NotificationDao = database.notificationDao()
}
