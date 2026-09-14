package com.shahvani.app.ui.screens.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahvani.app.data.local.entity.ProfileEntity
import com.shahvani.app.data.remote.dto.ProfileStatsDto
import com.shahvani.app.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileState(
    val profile: ProfileEntity? = null,
    val stats: ProfileStatsDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()
    
    private val username: String? = savedStateHandle["username"]
    
    fun loadProfile(username: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            val profileResult = profileRepository.getProfile(username)
            val statsResult = profileRepository.getProfileStats(username)
            
            profileResult.fold(
                onSuccess = { profile ->
                    _state.update { it.copy(profile = profile, error = null) }
                },
                onFailure = { error ->
                    _state.update { it.copy(error = error.message) }
                }
            )
            
            statsResult.fold(
                onSuccess = { stats ->
                    _state.update { it.copy(stats = stats) }
                },
                onFailure = { }
            )
            
            _state.update { it.copy(isLoading = false) }
        }
    }
}
