package com.shahvani.app.data.local.dao

import androidx.room.*
import com.shahvani.app.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    
    @Query("SELECT * FROM profiles WHERE username = :username")
    suspend fun getProfile(username: String): ProfileEntity?
    
    @Query("SELECT * FROM profiles WHERE username = :username")
    fun getProfileFlow(username: String): Flow<ProfileEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)
    
    @Update
    suspend fun updateProfile(profile: ProfileEntity)
    
    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)
    
    @Query("DELETE FROM profiles")
    suspend fun clearAll()
}
