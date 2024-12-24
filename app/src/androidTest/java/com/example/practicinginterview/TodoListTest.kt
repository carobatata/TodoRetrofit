package com.example.practicinginterview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.practicinginterview.api.TodoModel
import com.example.practicinginterview.ui.UiStateResponse
import com.example.practicinginterview.ui.screen.TodoListResult
import org.junit.Rule
import org.junit.Test

class TodoListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenResponseIsSuccessThenTheListShouldBeRendered(){
        // Given
        val todoList = listOf(
            TodoModel(
                id = 1,
                userId = 1,
                title = "title",
                completed = true
            )
        )

        // When
        composeTestRule.setContent {
            TodoListResult(UiStateResponse.Success(todoList))
        }

        // Then
        composeTestRule
            .onNodeWithTag("todo list")
            .assertIsDisplayed()
    }

    @Test
    fun whenIsLoadingThenItShouldRenderASpinner(){

        composeTestRule.setContent {
            TodoListResult(UiStateResponse.Loading)
        }

        composeTestRule
            .onNodeWithTag("spinner")
            .assertIsDisplayed()
    }

}