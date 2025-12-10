package com.example.myapplication.navigation

enum class Screen(val route: String) {
    MainScreen("main_screen"),
    SearchScreen("search_screen"),
    SettingsScreen("settings_screen")
    // Добавим PlaylistsScreen и FavoritesScreen позже если нужно
}