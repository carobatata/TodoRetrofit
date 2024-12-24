package com.example.practicinginterview.ui

import com.example.practicinginterview.api.TodoModel

sealed class UiStateResponse {
    data class Success(val todoList: List<TodoModel>): UiStateResponse()
    data object Error: UiStateResponse()
    data object Loading: UiStateResponse()
}