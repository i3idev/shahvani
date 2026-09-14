package com.shahvani.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumCategoryDto(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String? = null,
    val icon: String? = null,
    @SerialName("topics_count")
    val topicsCount: Int = 0,
    @SerialName("posts_count")
    val postsCount: Int = 0,
    @SerialName("last_post")
    val lastPost: PostDto? = null
)

@Serializable
data class TopicDto(
    val id: Int,
    val title: String,
    val slug: String,
    val content: String,
    val author: UserDto,
    @SerialName("forum_id")
    val forumId: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val views: Int = 0,
    val likes: Int = 0,
    val replies: Int = 0,
    val pinned: Boolean = false,
    val locked: Boolean = false,
    val bookmarked: Boolean = false,
    @SerialName("last_post")
    val lastPost: PostDto? = null,
    val posts: List<PostDto>? = null
)

@Serializable
data class PostDto(
    val id: Int,
    val content: String,
    val author: UserDto,
    @SerialName("topic_id")
    val topicId: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val likes: Int = 0,
    val liked: Boolean = false
)

@Serializable
data class CreateTopicRequest(
    val title: String,
    val content: String
)

@Serializable
data class CreatePostRequest(
    val content: String
)

@Serializable
data class EditPostRequest(
    val content: String
)

@Serializable
data class EditTopicRequest(
    val title: String? = null,
    val content: String? = null
)

@Serializable
data class LikeResponse(
    val success: Boolean,
    val likes: Int
)

@Serializable
data class LikesListDto(
    val users: List<UserDto>,
    val total: Int
)

@Serializable
data class BookmarkResponse(
    val success: Boolean,
    val message: String? = null
)
