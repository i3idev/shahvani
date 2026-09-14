package com.shahvani.app.data.local.dao

import androidx.room.*
import com.shahvani.app.data.local.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {
    
    @Query("SELECT * FROM topics WHERE bookmarked = 1 ORDER BY createdAt DESC")
    fun getBookmarkedTopics(): Flow<List<TopicEntity>>
    
    @Query("SELECT * FROM topics WHERE id = :id")
    suspend fun getTopicById(id: Int): TopicEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: TopicEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(topics: List<TopicEntity>)
    
    @Update
    suspend fun updateTopic(topic: TopicEntity)
    
    @Delete
    suspend fun deleteTopic(topic: TopicEntity)
    
    @Query("DELETE FROM topics")
    suspend fun clearAll()
}
