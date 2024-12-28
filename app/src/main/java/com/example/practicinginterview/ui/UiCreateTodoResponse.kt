package com.example.practicinginterview.ui

sealed class UiCreateTodoResponse {
    data object Success: UiCreateTodoResponse()
    data object Error: UiCreateTodoResponse()
    data object Loading: UiCreateTodoResponse()
}