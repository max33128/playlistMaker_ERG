package com.example.myapplication.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Button(onClick = onSearchClick) {
        Text("Перейти к поиску")
    }

    Button(onClick = onSettingsClick) {
        Text("Перейти к настройкам")
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        onSearchClick = {},
        onSettingsClick = {}
    )
}