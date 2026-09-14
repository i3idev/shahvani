package com.shahvani.app.di

import com.shahvani.app.core.network.ApiClient
import com.shahvani.app.data.remote.api.AuthApi
import com.shahvani.app.data.remote.api.ForumApi
import com.shahvani.app.data.remote.api.MessageApi
import com.shahvani.app.data.remote.api.NotificationApi
import com.shahvani.app.data.remote.api.ProfileApi
import com.shahvani.app.data.remote.api.SearchApi
import com.shahvani.app.data.remote.api.UploadApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
    
    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)
    
    @Provides
    @Singleton
    fun provideForumApi(retrofit: Retrofit): ForumApi = retrofit.create(ForumApi::class.java)
    
    @Provides
    @Singleton
    fun provideMessageApi(retrofit: Retrofit): MessageApi = retrofit.create(MessageApi::class.java)
    
    @Provides
    @Singleton
    fun provideNotificationApi(retrofit: Retrofit): NotificationApi = retrofit.create(NotificationApi::class.java)
    
    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)
    
    @Provides
    @Singleton
    fun provideUploadApi(retrofit: Retrofit): UploadApi = retrofit.create(UploadApi::class.java)
}
