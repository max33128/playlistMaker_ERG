package com.example.playlistmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme


class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistMakerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White // Явно указываем белый фон
                ) {
                    SearchScreen(
                        onSearch = { query ->
                            // Заглушка для будущей логики поиска
                            println("Search query: $query")
                        },
                        onBackClick = { finish() } // Кнопка "Назад"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}, // Заглушка для будущей логики поиска
    onBackClick: () -> Unit = {} // Для кнопки "Назад"
) {
    var searchQuery by remember { mutableStateOf("") }

    // Пример недавних поисков
    val recentSearches = remember {
        listOf(
            "The Beatles",
            "Yesterday",
            "Here Comes The Sun",
            "No Reply",
            "Let It Be"
        )
    }

    Scaffold(
        topBar = {
            // TopBar для красивого отображения и кнопки "Назад"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Поиск",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Поисковая строка (ниже заголовка)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    // Здесь будет вызываться логика поиска при изменении текста
                    onSearch(newValue)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        "Поиск",
                        color = Color.Gray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Поиск",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchQuery = ""
                                // Сброс поискового запроса
                                onSearch("")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Очистить поиск",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Список недавних поисков (отображается только если есть поисковые запросы)
            if (recentSearches.isNotEmpty() && searchQuery.isEmpty()) {
                Text(
                    text = "Недавние поиски",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn {
                    items(recentSearches) { search ->
                        RecentSearchItem(
                            query = search,
                            onItemClick = {
                                searchQuery = search
                                onSearch(search)
                            }
                        )
                    }
                }
            } else if (searchQuery.isNotEmpty()) {
                // Заглушка для результатов поиска
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Результаты поиска для: \"$searchQuery\"",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                // Пустое состояние
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Введите поисковый запрос",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun RecentSearchItem(
    query: String,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onItemClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Поиск",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = query,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    PlaylistMakerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            SearchScreen()
        }
    }
}