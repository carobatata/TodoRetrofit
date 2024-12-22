package com.example.practicinginterview

import TodoRepository
import com.example.practicinginterview.api.TodoApi
import com.example.practicinginterview.api.TodoModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class TodoRepositoryTest{

    @MockK
    private lateinit var api: TodoApi

    @InjectMockKs
    private lateinit var todoRepository: TodoRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `when api call is success then it should return success response and todo list`() = runTest {
        //Given
        val todos = listOf(
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

        coEvery { api.getAllTodos() } returns Response.success(todos)

        //When
        val result = todoRepository.getAllTodos()

        //Then
        val expected = Result.success(todos)
        assertEquals(result, expected)
    }

    @Test
    fun `when api call gives error then it should return error result`() = runTest {
        //Given
        val errorBody = ResponseBody.create(null, "Internal Server Error")
        coEvery { api.getAllTodos() } returns Response.error(500, errorBody)

        //When
        val result = todoRepository.getAllTodos()

        //Then
        assertTrue(result.isFailure)
    }
}