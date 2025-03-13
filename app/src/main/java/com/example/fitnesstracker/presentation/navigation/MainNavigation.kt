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
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Register
    ) {
        composable<Screen.Main> {
            MainScreen {
                navigateTo -> navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Register> {
            RegisterScreen {
                navigateTo -> navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Login> {
            LoginScreen {
                navigateTo -> navHostController.navigate(navigateTo)
            }
        }
    }
}