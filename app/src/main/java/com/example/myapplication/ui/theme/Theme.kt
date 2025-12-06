package com.example.playlistmaker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.Typography

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2196F3), // Основной синий цвет
    secondary = Color(0xFF1976D2), // Темно-синий
    tertiary = Color(0xFFBBDEFB), // Светло-синий
    background = Color(0xFFF5F5F5), // Светло-серый фон
    surface = Color.White, // Белый фон поверхностей
    onPrimary = Color.White, // Белый текст на синем
    onSecondary = Color.White, // Белый текст на темно-синем
    onTertiary = Color.Black, // Черный текст на светло-синем
    onBackground = Color.Black, // Черный текст на фоне
    onSurface = Color.Black, // Черный текст на поверхности
    outline = Color(0xFFE0E0E0) // Цвет границ
)

@Composable
fun PlaylistMakerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme, // Всегда используем светлую тему
        typography = Typography,
        content = content
    )
}