package com.example.myapplication.ui.viewmodel


import com.example.myapplication.domain.models.Track

sealed class SearchState {
    object Initial : SearchState() // Первоначальное состояние экрана
    object Searching : SearchState() // Состояние экрана при начале поиска

    data class Success(val list: List<Track>) : SearchState()
    data class Fail(val error: String) : SearchState() // Ошибка при запросе
}