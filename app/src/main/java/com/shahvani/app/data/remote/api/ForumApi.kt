package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.BookmarkResponse
import com.shahvani.app.data.remote.dto.CreatePostRequest
import com.shahvani.app.data.remote.dto.CreateTopicRequest
import com.shahvani.app.data.remote.dto.EditPostRequest
import com.shahvani.app.data.remote.dto.EditTopicRequest
import com.shahvani.app.data.remote.dto.ForumCategoryDto
import com.shahvani.app.data.remote.dto.LikeResponse
import com.shahvani.app.data.remote.dto.LikesListDto
import com.shahvani.app.data.remote.dto.TopicDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ForumApi {
    
    @GET("/api/v1/forum")
    suspend fun getCategories(): Response<List<ForumCategoryDto>>
    
    @GET("/api/v1/forum/topic/{slug}")
    suspend fun getTopic(@Path("slug") slug: String): Response<TopicDto>
    
    @POST("/api/v1/forum/{forumId}/topics")
    suspend fun createTopic(
        @Path("forumId") forumId: Int,
        @Body request: CreateTopicRequest
    ): Response<TopicDto>
    
    @POST("/api/v1/forum/topics/{topicId}/reply")
    suspend fun replyToTopic(
        @Path("topicId") topicId: Int,
        @Body request: CreatePostRequest
    ): Response<Unit>
    
    @PUT("/api/v1/forum/posts/{postId}")
    suspend fun editPost(
        @Path("postId") postId: Int,
        @Body request: EditPostRequest
    ): Response<Unit>
    
    @DELETE("/api/v1/forum/posts/{postId}")
    suspend fun deletePost(@Path("postId") postId: Int): Response<Unit>
    
    @DELETE("/api/v1/forum/topics/{topicId}")
    suspend fun deleteTopic(@Path("topicId") topicId: Int): Response<Unit>
    
    @PUT("/api/v1/forum/topics/{topicId}/content")
    suspend fun editTopic(
        @Path("topicId") topicId: Int,
        @Body request: EditTopicRequest
    ): Response<Unit>
    
    @POST("/api/v1/forum/topics/{topicId}/like")
    suspend fun likeTopic(@Path("topicId") topicId: Int): Response<LikeResponse>
    
    @POST("/api/v1/forum/posts/{postId}/like")
    suspend fun likePost(@Path("postId") postId: Int): Response<LikeResponse>
    
    @GET("/api/v1/forum/topics/{topicId}/likes")
    suspend fun getTopicLikes(@Path("topicId") topicId: Int): Response<LikesListDto>
    
    @GET("/api/v1/forum/posts/{postId}/likes")
    suspend fun getPostLikes(@Path("postId") postId: Int): Response<LikesListDto>
    
    @POST("/api/v1/forum/bookmarks")
    suspend fun addBookmark(@Body body: Map<String, Int>): Response<BookmarkResponse>
    
    @DELETE("/api/v1/forum/bookmarks/{topicId}")
    suspend fun removeBookmark(@Path("topicId") topicId: Int): Response<BookmarkResponse>
}
