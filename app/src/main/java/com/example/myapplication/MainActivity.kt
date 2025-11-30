package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PlaylistMakerScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistMakerScreen() {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ЗАГОЛОВОК И КНОПКА ДОБАВЛЕНИЯ С СИНИМ ФОНОМ
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2196F3))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Playlist maker",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                IconButton(
                    onClick = {
                        // ПЕРЕХОД НА ЭКРАН ПОИСКА ПРИ НАЖАТИИ НА "+"
                        val intent = Intent(context, SearchActivity::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Добавить плейлист",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ПОЛЕ ПОИСКА (УПРОЩЕННАЯ ВЕРСИЯ БЕЗ onFocusChange)
        TextField(
            value = searchText,
            onValueChange = { newText -> searchText = newText },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Поиск"
                )
            },
            placeholder = {
                Text("Поиск")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // СПИСОК МЕНЮ
        Column {
            // Пункт "Плейлисты"
            Card(
                onClick = {
                    // ПЕРЕХОД НА ЭКРАН ПОИСКА
                    val intent = Intent(context, SearchActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Плейлисты",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Перейти"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Пункт "Избранное"
            Card(
                onClick = {
                    // ПЕРЕХОД НА ЭКРАН ПОИСКА
                    val intent = Intent(context, SearchActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Избранное",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Перейти"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Пункт "Настройки"
            Card(
                onClick = {
                    // ПЕРЕХОД НА ЭКРАН НАСТРОЕК
                    val intent = Intent(context, SettingsActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Настройки",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Перейти"
                    )
                }
            }
        }
    }
}