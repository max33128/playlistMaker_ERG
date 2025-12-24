
package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val darkTheme = remember { mutableStateOf(false) }
    val notifications = remember { mutableStateOf(true) }
    val autoPlay = remember { mutableStateOf(true) }
    val highQuality = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Настройки",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Внешний вид
        Card(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Внешний вид",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.DarkMode,
                            contentDescription = "Тема",
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text("Темная тема")
                    }
                    Switch(
                        checked = darkTheme.value,
                        onCheckedChange = { darkTheme.value = it }
                    )
                }
            }
        }

        // Уведомления
        Card(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Уведомления",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Уведомления",
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text("Push-уведомления")
                    }
                    Switch(
                        checked = notifications.value,
                        onCheckedChange = { notifications.value = it }
                    )
                }
            }
        }

        // Воспроизведение
        Card(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Воспроизведение",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Автовоспроизведение",
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text("Автовоспроизведение")
                    }
                    Switch(
                        checked = autoPlay.value,
                        onCheckedChange = { autoPlay.value = it }
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.HighQuality,
                            contentDescription = "Качество",
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text("Высокое качество")
                    }
                    Switch(
                        checked = highQuality.value,
                        onCheckedChange = { highQuality.value = it }
                    )
                }
            }
        }

        // О приложении
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "О приложении",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                SettingItem(
                    icon = Icons.Filled.Info,
                    text = "Версия",
                    value = "1.0.0"
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                SettingItem(
                    icon = Icons.Filled.Policy,
                    text = "Политика конфиденциальности",
                    onClick = { /* Открыть политику */ }
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                SettingItem(
                    icon = Icons.Filled.Description,
                    text = "Пользовательское соглашение",
                    onClick = { /* Открыть соглашение */ }
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                SettingItem(
                    icon = Icons.Filled.Email,
                    text = "Обратная связь",
                    onClick = { /* Открыть форму обратной связи */ }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    value: String? = null,
    onClick: (() -> Unit)? = null
) {
    val content = @Composable {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(text, modifier = Modifier.weight(1f))
            value?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }

    if (onClick != null) {
        Card(
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            content()
        }
    } else {
        content()
    }
}