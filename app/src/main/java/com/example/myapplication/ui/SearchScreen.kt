
package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.KeyboardType


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.domain.models.Track
import com.example.myapplication.ui.viewmodel.SearchState
import com.example.myapplication.ui.viewmodel.SearchViewModel
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModel.getViewModelFactory())
    val searchState by viewModel.searchScreenState.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Заголовок
        Text(
            text = "Поиск треков",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Поле поиска
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Введите название трека") },
            placeholder = { Text("Например: Кино, Сплин, Ария...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search, // Меняем иконку Enter на "лупу"
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Выполняем поиск при нажатии Enter/Поиск на клавиатуре
                    if (searchQuery.isNotEmpty()) {
                        viewModel.search(searchQuery)
                    }
                }
            ),
            leadingIcon = {
                Icon(Icons.Default.Search, "Поиск")
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, "Очистить")
                    }
                }
            }
        )

        // Кнопка поиска
        Button(
            onClick = {
                if (searchQuery.isNotEmpty()) {
                    viewModel.search(searchQuery)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = searchQuery.isNotEmpty() && searchState !is SearchState.Searching
        ) {
            Text(
                text = when (searchState) {
                    is SearchState.Searching -> "Идет поиск..."
                    else -> "Найти треки"
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Результаты поиска
        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = searchState) {
                is SearchState.Initial -> {
                    InitialState()
                }

                is SearchState.Searching -> {
                    LoadingState()
                }

                is SearchState.Success -> {
                    if (state.list.isEmpty()) {
                        EmptyState()
                    } else {
                        ResultsState(tracks = state.list)
                    }
                }

                is SearchState.Fail -> {
                    ErrorState(
                        error = state.error,
                        onRetry = { viewModel.search(searchQuery) }
                    )
                }
            }
        }
    }
}

@Composable
private fun InitialState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Поиск",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Начните поиск",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Введите запрос для поиска треков",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Поиск треков...")
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ничего не найдено",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Попробуйте другой запрос",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun ResultsState(tracks: List<Track>) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Используем слово "треков" с правильным окончанием
        val trackCount = tracks.size
        val trackWord = when {
            trackCount % 10 == 1 && trackCount % 100 != 11 -> "трек"
            trackCount % 10 in 2..4 && trackCount % 100 !in 12..14 -> "трека"
            else -> "треков"
        }

        Text(
            text = "Найдено: $trackCount $trackWord",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tracks) { track ->
                TrackItem(track = track)
            }
        }
    }
}


@Composable
private fun ErrorState(error: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ошибка",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error
        )
        Text(
            text = error,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(onClick = onRetry) {
            Text("Повторить")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackItem(track: Track) {
    Card(
        onClick = { /* Действие при нажатии на трек */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = track.trackName,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = track.artistName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Длительность: ${track.trackTime}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}