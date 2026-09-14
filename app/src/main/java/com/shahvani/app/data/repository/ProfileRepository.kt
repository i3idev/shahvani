package com.shahvani.app.data.repository

import com.shahvani.app.data.local.dao.ProfileDao
import com.shahvani.app.data.local.entity.ProfileEntity
import com.shahvani.app.data.remote.api.ProfileApi
import com.shahvani.app.data.remote.dto.GalleryDto
import com.shahvani.app.data.remote.dto.ProfileContentDto
import com.shahvani.app.data.remote.dto.ProfileStatsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileApi: ProfileApi,
    private val profileDao: ProfileDao
) {
    fun getProfileFlow(username: String): Flow<ProfileEntity?> = profileDao.getProfileFlow(username)
    
    suspend fun getProfile(username: String, forceRefresh: Boolean = false): Result<ProfileEntity> {
        return try {
            if (!forceRefresh) {
                val cached = profileDao.getProfile(username)
                if (cached != null && !isCacheExpired(cached.cachedAt)) {
                    return Result.success(cached)
                }
            }
            
            val response = profileApi.getProfile(username)
            if (response.isSuccessful && response.body() != null) {
                val dto = response.body()!!
                val entity = ProfileEntity(
                    id = dto.id,
                    username = dto.username,
                    avatar = dto.avatar,
                    cover = dto.cover,
                    bio = dto.bio,
                    gender = dto.gender,
                    location = dto.location,
                    website = dto.website,
                    createdAt = dto.createdAt,
                    lastSeen = dto.lastSeen,
                    verified = dto.verified,
                    online = dto.online,
                    blocked = dto.blocked
                )
                profileDao.insertProfile(entity)
                Result.success(entity)
            } else {
                Result.failure(Exception("Failed to load profile"))
            }
        } catch (e: Exception) {
            val cached = profileDao.getProfile(username)
            if (cached != null) {
                Result.success(cached)
            } else {
                Result.failure(e)
            }
        }
    }
    
    suspend fun getProfileStats(username: String): Result<ProfileStatsDto> {
        return try {
            val response = profileApi.getProfileStats(username)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load stats"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getProfileContent(
        username: String,
        type: String,
        limit: Int? = null,
        offset: Int? = null
    ): Result<ProfileContentDto> {
        return try {
            val response = profileApi.getProfileContent(username, type, limit, offset)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load content"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getGallery(username: String, page: Int? = null, limit: Int? = null): Result<GalleryDto> {
        return try {
            val response = profileApi.getGallery(username, page, limit)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load gallery"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun blockUser(userId: Int): Result<Unit> {
        return try {
            val response = profileApi.blockUser(userId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to block user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun unblockUser(userId: Int): Result<Unit> {
        return try {
            val response = profileApi.unblockUser(userId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to unblock user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun isCacheExpired(cachedAt: Long): Boolean {
        val cacheDurationMs = 5 * 60 * 1000L // 5 minutes
        return System.currentTimeMillis() - cachedAt > cacheDurationMs
    }
}
