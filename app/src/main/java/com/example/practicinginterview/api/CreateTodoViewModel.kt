package com.example.practicinginterview.api

import android.util.Log
import androidx.lifecycle.ViewModel

class CreateTodoViewModel: ViewModel() {

    fun createTodo(todo: String){
        Log.i("Create Todo ViewModel", "I created: $todo")
    }

}