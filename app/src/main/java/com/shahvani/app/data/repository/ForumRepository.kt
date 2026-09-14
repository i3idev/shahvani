package com.shahvani.app.data.repository

import com.shahvani.app.core.network.CsrfTokenManager
import com.shahvani.app.data.remote.api.ForumApi
import com.shahvani.app.data.remote.dto.BookmarkResponse
import com.shahvani.app.data.remote.dto.CreatePostRequest
import com.shahvani.app.data.remote.dto.CreateTopicRequest
import com.shahvani.app.data.remote.dto.EditPostRequest
import com.shahvani.app.data.remote.dto.EditTopicRequest
import com.shahvani.app.data.remote.dto.ForumCategoryDto
import com.shahvani.app.data.remote.dto.LikeResponse
import com.shahvani.app.data.remote.dto.LikesListDto
import com.shahvani.app.data.remote.dto.TopicDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForumRepository @Inject constructor(
    private val forumApi: ForumApi,
    private val csrfTokenManager: CsrfTokenManager
) {
    suspend fun getCategories(): Result<List<ForumCategoryDto>> {
        return try {
            val response = forumApi.getCategories()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load categories"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTopic(slug: String): Result<TopicDto> {
        return try {
            val response = forumApi.getTopic(slug)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load topic"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun createTopic(forumId: Int, title: String, content: String): Result<TopicDto> {
        return withCsrfToken {
            val response = forumApi.createTopic(forumId, CreateTopicRequest(title, content))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to create topic"))
            }
        }
    }
    
    suspend fun replyToTopic(topicId: Int, content: String): Result<Unit> {
        return withCsrfToken {
            val response = forumApi.replyToTopic(topicId, CreatePostRequest(content))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to reply"))
            }
        }
    }
    
    suspend fun editPost(postId: Int, content: String): Result<Unit> {
        return withCsrfToken {
            val response = forumApi.editPost(postId, EditPostRequest(content))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to edit post"))
            }
        }
    }
    
    suspend fun deletePost(postId: Int): Result<Unit> {
        return withCsrfToken {
            val response = forumApi.deletePost(postId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete post"))
            }
        }
    }
    
    suspend fun deleteTopic(topicId: Int): Result<Unit> {
        return withCsrfToken {
            val response = forumApi.deleteTopic(topicId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete topic"))
            }
        }
    }
    
    suspend fun editTopic(topicId: Int, title: String?, content: String?): Result<Unit> {
        return withCsrfToken {
            val response = forumApi.editTopic(topicId, EditTopicRequest(title, content))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to edit topic"))
            }
        }
    }
    
    suspend fun likeTopic(topicId: Int): Result<LikeResponse> {
        return try {
            val response = forumApi.likeTopic(topicId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to like topic"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun likePost(postId: Int): Result<LikeResponse> {
        return try {
            val response = forumApi.likePost(postId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to like post"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTopicLikes(topicId: Int): Result<LikesListDto> {
        return try {
            val response = forumApi.getTopicLikes(topicId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load likes"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getPostLikes(postId: Int): Result<LikesListDto> {
        return try {
            val response = forumApi.getPostLikes(postId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load likes"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun addBookmark(topicId: Int): Result<BookmarkResponse> {
        return withCsrfToken {
            val response = forumApi.addBookmark(mapOf("topicId" to topicId))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to add bookmark"))
            }
        }
    }
    
    suspend fun removeBookmark(topicId: Int): Result<BookmarkResponse> {
        return withCsrfToken {
            val response = forumApi.removeBookmark(topicId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to remove bookmark"))
            }
        }
    }
    
    private suspend fun <T> withCsrfToken(action: suspend () -> Result<T>): Result<T> {
        val csrfResult = csrfTokenManager.fetchToken()
        return if (csrfResult.isSuccess) {
            action()
        } else {
            Result.failure(csrfResult.exceptionOrNull() ?: Exception("CSRF token fetch failed"))
        }
    }
}
