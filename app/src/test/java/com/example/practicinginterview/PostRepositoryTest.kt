package com.example.practicinginterview

import com.example.practicinginterview.api.PostModel
import com.example.practicinginterview.api.TodoApi
import com.example.practicinginterview.posts.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PostRepositoryTest {

    @InjectMockKs
    private lateinit var postRepository: PostRepository

    @MockK
    private lateinit var api: TodoApi

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `when response is success then it should return success with posts`() = runTest {
        // Given
        val postOne = PostModel(
            userId = 1,
            id = 1,
            title = "Post 1",
            body = "Post 1 body"
        )

        val postTwo = PostModel(
            userId = 2,
            id = 2,
            title = "Post 2",
            body = "Post 2 body"
        )

        val postList = listOf(postOne, postTwo)

        coEvery { api.getAllPosts() } returns Response.success(postList)

        // When
        val result = postRepository.getAllPosts()

        // Then
        val expected = Result.success(postList)
        assertEquals(expected, result)
    }

    @Test
    fun `when response is success but empty body then return failure`() = runTest {
        // Given
        coEvery { api.getAllPosts() } returns Response.success(null)

        // When
        val result = postRepository.getAllPosts()

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `when response fails then it should return failure`() = runTest {
        // Given
        val responseBody = ResponseBody.create(
            MediaType.parse(""),
            ""
        )
        coEvery { api.getAllPosts() } returns Response.error(500, responseBody)

        // When
        val result = postRepository.getAllPosts()

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `when response is success then it should return success and the post`() = runTest {
        // Given
        val post = PostModel(
            id = 1,
            userId = 1,
            title = "Title",
            body = "Body"
        )

        coEvery { api.getPost(1) } returns Response.success(post)

        // When
        val result = postRepository.getPost(1)

        // Then
        val expected = Result.success(post)
        assertEquals(expected, result)

    }

}