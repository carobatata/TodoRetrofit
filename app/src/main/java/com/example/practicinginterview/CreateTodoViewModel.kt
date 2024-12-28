package com.example.practicinginterview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicinginterview.posts.PostRepository
import com.example.practicinginterview.ui.UiCreateTodoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateTodoViewModel(private val repository: PostRepository): ViewModel() {

    private val _todoName = MutableStateFlow("")
    val todoName: StateFlow<String> = _todoName

    private val _createTodoUiState = MutableStateFlow<UiCreateTodoResponse>(UiCreateTodoResponse.Loading)
    var createTodoUIState: StateFlow<UiCreateTodoResponse> = _createTodoUiState

    private val _todoOptions = MutableStateFlow("Options")
    var todoOptions: StateFlow<String> = _todoOptions

    fun createTodo(){
        _createTodoUiState.value = UiCreateTodoResponse.Loading
        viewModelScope.launch {
            val response = repository.createPost(
                _todoName.value
            )
            if(response.isSuccess) {
                _createTodoUiState.value = UiCreateTodoResponse.Success
            } else {
                _createTodoUiState.value = UiCreateTodoResponse.Error
            }
        }
    }

    fun updateTodoName(newName: String){
        _todoName.value = newName
    }

    fun updateTodoOption(option: String){
        _todoOptions.value = option
    }
}