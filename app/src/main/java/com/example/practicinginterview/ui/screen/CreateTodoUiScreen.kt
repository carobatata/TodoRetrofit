package com.example.practicinginterview.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CreateTodoUiScreen(name: String, navigateToThirdScreen: () -> Unit, onBackButton: () -> Unit) {
    Column {
        Text("Hello, you are in the other screen. My name is $name")
        Button(
            onClick = navigateToThirdScreen
        ) {
            Text("Click me to go to third Screen")
        }
        Button(
            onClick = { onBackButton() }
        ) {
            Text("Click me to go to last screen")
        }
    }
}