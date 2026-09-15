package com.shahvani.app.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.shahvani.app.R
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
                onCategoryClick = { _, slug ->
                    navController.navigate(Screen.Topic(slug))
                }
            )
        }

        composable<Screen.Topic> {
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
            PlaceholderScreen(title = stringResource(R.string.messages))
        }

        composable<Screen.Notifications> {
            PlaceholderScreen(title = stringResource(R.string.notifications))
        }

        composable<Screen.Settings> {
            PlaceholderScreen(title = stringResource(R.string.settings))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceholderScreen(title: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title)
        }
    }
}
