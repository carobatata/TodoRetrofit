package com.example.practicinginterview.ui

import kotlinx.serialization.Serializable

//routes
@Serializable
object TodoListScreen

@Serializable
data class CreateTodoScreen(
    val name: String
)