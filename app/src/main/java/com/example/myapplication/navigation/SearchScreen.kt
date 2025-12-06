package com.example.myapplication.navigation


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchScreen(
    onBackClick: () -> Unit
) {
    Text("Экран поиска")
    Button(onClick = onBackClick) {
        Text("Назад")
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(onBackClick = {})
}