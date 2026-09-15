package com.shahvani.app.ui.screens.forum

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahvani.app.data.remote.dto.PostDto
import com.shahvani.app.data.remote.dto.TopicDto
import com.shahvani.app.data.repository.ForumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TopicState(
    val topic: TopicDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isReplying: Boolean = false,
    val replyContent: String = "",
    val topicLiked: Boolean = false
)

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val forumRepository: ForumRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val _state = MutableStateFlow(TopicState())
    val state = _state.asStateFlow()
    
    private val slug: String? = savedStateHandle["slug"]
    
    init {
        slug?.let { loadTopic(it) }
    }
    
    fun loadTopic(slug: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = forumRepository.getTopic(slug)
            result.fold(
                onSuccess = { topic ->
                    _state.update { it.copy(isLoading = false, topic = topic) }
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
            )
        }
    }
    
    fun onReplyContentChange(content: String) {
        _state.update { it.copy(replyContent = content) }
    }
    
    fun submitReply() {
        val topicId = _state.value.topic?.id ?: return
        val content = _state.value.replyContent.trim()
        if (content.isEmpty()) return
        
        viewModelScope.launch {
            _state.update { it.copy(isReplying = true) }
            val result = forumRepository.replyToTopic(topicId, content)
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isReplying = false, replyContent = "") }
                    slug?.let { loadTopic(it) }
                },
                onFailure = { error ->
                    _state.update { it.copy(isReplying = false, error = error.message) }
                }
            )
        }
    }
    
    fun likeTopic() {
        val topicId = _state.value.topic?.id ?: return
        val isLiked = _state.value.topicLiked
        viewModelScope.launch {
            val result = forumRepository.likeTopic(topicId)
            result.fold(
                onSuccess = {
                    _state.update { s ->
                        s.copy(
                            topicLiked = it.userLike ?: !isLiked,
                            topic = s.topic?.copy(likes = it.likes)
                        )
                    }
                },
                onFailure = { error ->
                    _state.update { it.copy(error = error.message) }
                }
            )
        }
    }

    fun likePost(postId: Int) {
        val topicId = _state.value.topic?.id ?: return
        viewModelScope.launch {
            val result = forumRepository.likePost(postId)
            result.fold(
                onSuccess = {
                    slug?.let { loadTopic(it) }
                },
                onFailure = { error ->
                    _state.update { it.copy(error = error.message) }
                }
            )
        }
    }

    fun toggleBookmark() {
        val topicId = _state.value.topic?.id ?: return
        val isBookmarked = _state.value.topic?.bookmarked ?: false

        viewModelScope.launch {
            if (isBookmarked) {
                forumRepository.removeBookmark(topicId)
            } else {
                forumRepository.addBookmark(topicId)
            }
            slug?.let { loadTopic(it) }
        }
    }
}
