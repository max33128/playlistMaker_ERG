package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.MainScreen
import com.example.myapplication.ui.MediaLibraryScreen
import com.example.myapplication.ui.SearchScreen
import com.example.myapplication.ui.SettingsScreen

@Composable
fun PlaylistHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {
        // Экран Main
        composable(Screen.Main.route) {
            MainScreen(
                onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                onNavigateToMediaLibrary = { navController.navigate(Screen.MediaLibrary.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }

        // Экран MediaLibrary
        composable(Screen.MediaLibrary.route) {
            MediaLibraryScreen()
        }

        // Экран Search (СТАРТОВЫЙ)
        composable(Screen.Search.route) {
            SearchScreen()
        }

        // Экран Settings
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}