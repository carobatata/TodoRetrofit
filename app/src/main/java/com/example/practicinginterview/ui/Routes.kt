package com.example.practicinginterview.ui

import kotlinx.serialization.Serializable

@Serializable
object TodoListScreen

@Serializable
data class CreateTodoScreen(
    val name: String
)

@Serializable
object CreateTodoForm

@Serializable
object FlightScreenRoute

@Serializable
object CounterScreenRoute