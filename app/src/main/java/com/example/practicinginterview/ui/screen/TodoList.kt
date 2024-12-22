package com.example.practicinginterview.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicinginterview.TodoViewModel
import com.example.practicinginterview.ui.theme.PracticingInterviewTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun TodoList(){

    val todoViewModel = getViewModel<TodoViewModel>()

    val todoList = todoViewModel.todoItems.collectAsState()

    LazyColumn {
        items(
            key = {item -> item.id},
            items = todoList.value
        ) { item ->
            ListItem(
                headlineContent = { Text(item.title)  },
                supportingContent = { Text(item.completed.toString()) },
                leadingContent = {
                    Icon(Icons.Default.Star, "")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    PracticingInterviewTheme {
        TodoList()
    }
}