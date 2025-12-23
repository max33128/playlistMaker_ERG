package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.MainScreen
import com.example.myapplication.ui.SearchScreen
import com.example.myapplication.ui.SettingsScreen


@Composable
fun PlaylistHost(
    navController: NavHostController
) {
    // NavHost requires a NavHostController specifically
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                onNavigateToSearch = {
                    navigateToSearch(navController)
                },
                onNavigateToSettings = {
                    navigateToSettings(navController)
                }
            )
        }

        composable(Screen.SearchScreen.route) {
            SearchScreen(
                onSearch = { query ->
                    println("Поиск: $query")
                },
                onBackClick = {
                    navigateBack(navController)
                }
            )
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(
                onBackClick = {
                    navigateBack(navController)
                }
            )
        }
    }
}

/**
 * Методы для переходов между экранами
 * Должны использовать NavController и enum-класс Screen
 */

// ✅ Метод перехода на экран поиска
fun navigateToSearch(navController: NavController) {
    navController.navigate(Screen.SearchScreen.route)
}

// ✅ Метод перехода на экран настроек
fun navigateToSettings(navController: NavController) {
    navController.navigate(Screen.SettingsScreen.route)
}

// ✅ Метод возврата назад
fun navigateBack(navController: NavController) {
    navController.popBackStack()
}

// ✅ Дополнительный метод: переход на главный экран
fun navigateToMain(navController: NavController) {
    navController.navigate(Screen.MainScreen.route) {
        popUpTo(Screen.MainScreen.route) {
            inclusive = true
        }
    }
}