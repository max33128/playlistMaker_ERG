package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.MainScreen
import com.example.myapplication.SearchScreen
import com.example.myapplication.SettingsScreen

@Composable
fun PlaylistHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                onSearchClick = { navigateToSearch(navController) },
                onSettingsClick = { navigateToSettings(navController) }
            )
        }

        composable(Screen.SearchScreen.route) {
            SearchScreen(
                onBackClick = { navigateBack(navController) }
            )
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(
                onBackClick = { navigateBack(navController) }
            )
        }
    }
}

// Методы навигации
fun navigateToMain(navController: NavHostController) {
    navController.navigate(Screen.MainScreen.route) {
        popUpTo(Screen.MainScreen.route) { inclusive = true }
    }
}

fun navigateToSearch(navController: NavHostController) {
    navController.navigate(Screen.SearchScreen.route)
}

fun navigateToSettings(navController: NavHostController) {
    navController.navigate(Screen.SettingsScreen.route)
}

fun navigateBack(navController: NavHostController) {
    navController.popBackStack()
}