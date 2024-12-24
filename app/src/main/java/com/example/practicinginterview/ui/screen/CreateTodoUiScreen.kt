package com.example.practicinginterview.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CreateTodoUiScreen(name: String) {
    Column {
        Text("Heelllo, you are in the other screen. My name is $name")
    }
}