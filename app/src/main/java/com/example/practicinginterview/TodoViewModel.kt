package com.example.practicinginterview

import TodoRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicinginterview.ui.UiStateResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private var _todoItems = MutableStateFlow<UiStateResponse>(UiStateResponse.Loading)
    val todoItems: StateFlow<UiStateResponse> = _todoItems

    init {
        getAllTodos()
    }

     fun getAllTodos() {
        _todoItems.value = UiStateResponse.Loading
        viewModelScope.launch {
            val response = repository.getAllTodos()
            response.onSuccess { todos ->
                _todoItems.value = UiStateResponse.Success(todos!!)
            }
        }
    }
}