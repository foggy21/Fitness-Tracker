package com.example.fitnesstracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnesstracker.presentation.screen.LoginScreen
import com.example.fitnesstracker.presentation.screen.MainScreen
import com.example.fitnesstracker.presentation.screen.RegisterScreen
import kotlinx.serialization.Serializable

typealias NavigationCallback = (Screen) -> Unit

sealed class Screen {
    @Serializable
    data object Main : Screen()
    @Serializable
    data object Login : Screen()
    @Serializable
    data object Register : Screen()
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val navigationCallback: NavigationCallback = { screen ->
        navHostController.navigate(screen)
    }

    NavHost(
        navController = navHostController,
        startDestination = Screen.Main,
        modifier = modifier
    ) {
        composable<Screen.Main> {
            MainScreen(onNavigateTo = navigationCallback)
        }
        composable<Screen.Register> {
            RegisterScreen(onNavigateTo = navigationCallback)
        }
        composable<Screen.Login> {
            LoginScreen(onNavigateTo = navigationCallback)
        }
    }
}