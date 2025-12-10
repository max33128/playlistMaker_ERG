package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.PlaylistPlay
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.GrayBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Playlist maker",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BluePrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(GrayBackground),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Меню-кнопки начинаются сразу после TopAppBar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp) // Небольшой отступ от топ-бара (можно уменьшить или убрать)
            ) {
                MenuButton(
                    icon = Icons.Default.Search,
                    title = "Поиск",
                    onClick = onNavigateToSearch,
                    addTopDivider = true
                )
                MenuButton(
                    icon = Icons.Outlined.PlaylistPlay,
                    title = "Плейлисты",
                    onClick = { /* TODO: Navigate to playlists */ }
                )
                MenuButton(
                    icon = Icons.Default.Favorite,
                    title = "Избранное",
                    onClick = { /* TODO: Navigate to favorites */ }
                )
                MenuButton(
                    icon = Icons.Default.Settings,
                    title = "Настройки",
                    onClick = onNavigateToSettings,
                    hasBottomDivider = false
                )
            }
        }
    }
}

@Composable
fun MenuButton(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    addTopDivider: Boolean = false,
    hasBottomDivider: Boolean = true
) {
    var modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .height(64.dp)

    if (addTopDivider) {
        modifier = modifier.border(
            width = 0.5.dp,
            color = Color.LightGray.copy(alpha = 0.5f),
            shape = RoundedCornerShape(0.dp)
        )
    }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Перейти",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }

        if (hasBottomDivider) {
            HorizontalDivider(
                thickness = 0.5.dp,
                color = Color.LightGray.copy(alpha = 0.5f),
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}