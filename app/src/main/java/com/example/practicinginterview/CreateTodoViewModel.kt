package com.example.practicinginterview

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateTodoViewModel: ViewModel() {

    private val _todoName = MutableStateFlow("")
    val todoName: StateFlow<String> = _todoName

    private val _todoOptions = MutableStateFlow("Options")
    var todoOptions: StateFlow<String> = _todoOptions

    fun createTodo(todo: String){
        Log.i("Create Todo ViewModel", "I created: $todo")
    }

    fun updateTodoName(newName: String){
        _todoName.value = newName
    }

    fun updateTodoOption(option: String){
        _todoOptions.value = option
    }
}