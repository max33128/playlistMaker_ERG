// Файл: navigation/Screens.kt
package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object MediaLibrary : Screen("media_library")
    object Search : Screen("search")
    object Settings : Screen("settings")
}