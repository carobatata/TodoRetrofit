package com.example.practicinginterview

import TodoRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicinginterview.api.TodoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()

    private var _todoItems = MutableStateFlow(emptyList<TodoModel>())
    val todoItems: StateFlow<List<TodoModel>> = _todoItems

    init {
        getAllTodos()
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            val response = repository.getAllTodos()
            response.onSuccess { todos ->
                _todoItems.value = todos!!
            }
        }
    }
}