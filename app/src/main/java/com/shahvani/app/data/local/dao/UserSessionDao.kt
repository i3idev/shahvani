package com.shahvani.app.data.local.dao

import androidx.room.*
import com.shahvani.app.data.local.entity.UserSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserSessionDao {
    
    @Query("SELECT * FROM user_session WHERE id = 0")
    suspend fun getSession(): UserSessionEntity?
    
    @Query("SELECT * FROM user_session WHERE id = 0")
    fun getSessionFlow(): Flow<UserSessionEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(session: UserSessionEntity)
    
    @Query("DELETE FROM user_session")
    suspend fun clearSession()
}
