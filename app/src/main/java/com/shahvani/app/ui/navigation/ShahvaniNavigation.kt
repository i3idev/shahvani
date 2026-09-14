package com.shahvani.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.shahvani.app.ui.screens.auth.LoginScreen
import com.shahvani.app.ui.screens.forum.TopicScreen
import com.shahvani.app.ui.screens.home.HomeScreen
import com.shahvani.app.ui.screens.profile.ProfileScreen

@Composable
fun ShahvaniNavigation(
    navController: NavHostController,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home) {
                        popUpTo(Screen.Login) { inclusive = true }
                    }
                }
            )
        }
        
        composable<Screen.Home> {
            HomeScreen(
                onCategoryClick = { categoryId, slug ->
                    navController.navigate(Screen.Topic(slug))
                }
            )
        }
        
        composable<Screen.Topic> { backStackEntry ->
            val route = backStackEntry.toRoute<Screen.Topic>()
            TopicScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable<Screen.Profile> { backStackEntry ->
            val route = backStackEntry.toRoute<Screen.Profile>()
            ProfileScreen(
                username = route.username,
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings)
                }
            )
        }
        
        composable<Screen.Messages> {
            // TODO: MessagesScreen
        }
        
        composable<Screen.Notifications> {
            // TODO: NotificationsScreen
        }
        
        composable<Screen.Settings> {
            // TODO: SettingsScreen
        }
    }
}
