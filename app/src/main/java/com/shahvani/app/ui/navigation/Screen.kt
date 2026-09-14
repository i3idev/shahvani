package com.shahvani.app.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Login : Screen
    
    @Serializable
    data object Home : Screen
    
    @Serializable
    data class Topic(val slug: String) : Screen
    
    @Serializable
    data class Profile(val username: String) : Screen
    
    @Serializable
    data object Messages : Screen
    
    @Serializable
    data object Notifications : Screen
    
    @Serializable
    data object Settings : Screen
}
