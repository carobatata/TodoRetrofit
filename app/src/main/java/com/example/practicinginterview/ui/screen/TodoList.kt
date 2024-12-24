package com.example.practicinginterview.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicinginterview.TodoViewModel
import com.example.practicinginterview.api.TodoModel
import com.example.practicinginterview.ui.UiStateResponse
import com.example.practicinginterview.ui.theme.PracticingInterviewTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun TodoList(){

    val todoViewModel = getViewModel<TodoViewModel>()

    val todoUiResponse = todoViewModel.todoItems.collectAsState()

    TodoListResult(todoUiResponse.value)
}

@Composable
fun TodoListResult(todoListUiState: UiStateResponse) {
    when (todoListUiState) {
        UiStateResponse.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.testTag("spinner")
            )
        }

        is UiStateResponse.Success -> {
            LazyColumn(modifier = Modifier.testTag("todo list")) {
                items(
                    items = todoListUiState.todoList,
                    key = { item -> item.id }
                ) { item ->
                    ListItem(
                        headlineContent = { Text(item.title) },
                        supportingContent = { Text(item.completed.toString()) },
                        leadingContent = {
                            Icon(Icons.Default.Star, "")
                        }
                    )
                }
            }
        }

        UiStateResponse.Error -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    val todoItemsList = listOf(
        TodoModel(
            userId = 1,
            id = 1,
            title = "title",
            completed = false
        ),
        TodoModel(
            userId = 2,
            id = 2,
            title = "title2",
            completed = false
        )
    )
    PracticingInterviewTheme {
        TodoListResult(UiStateResponse.Success(todoItemsList))
    }
}