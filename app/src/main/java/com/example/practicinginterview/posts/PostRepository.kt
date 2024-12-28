package com.example.practicinginterview.posts

import com.example.practicinginterview.api.PostModel
import com.example.practicinginterview.api.TodoApi
import kotlin.random.Random

class PostRepository(private val api: TodoApi) {

    suspend fun getAllPosts(): Result<List<PostModel>> {
        return try {
            val response = api.getAllPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body null"))
            } else {
                Result.failure(Exception("API error: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPost(id: Int): Result<PostModel> {
        return try {
            val response = api.getPost(id)
            if (response.isSuccessful) {
                (response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body null")))
            } else {
                Result.failure(Exception("API error: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deletePost(id: Int): Result<Unit> {
        return try {
            val response = api.deletePost(id)
            if (response.isSuccessful){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete post"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun createPost(post: String): Result<Unit> {
        val randomNumber = Random.nextInt(101)
        val postModel = PostModel(
            id = randomNumber,
            userId = randomNumber,
            title = post,
            body = post
        )
        return try {
            val response = api.createPost(postModel)
            if (response.isSuccessful){
                Result.success(Unit)
            } else {
                Result.failure(Exception("API error: ${response.message()}"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}