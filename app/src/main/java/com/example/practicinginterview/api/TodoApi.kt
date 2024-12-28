package com.example.practicinginterview.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {

    @GET("/todos")
    suspend fun getAllTodos(): Response<List<TodoModel>>

    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostModel>>

    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Response<PostModel>

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>

    @POST("/posts")
    suspend fun createPost(@Body post: PostModel): Response<Unit>
}

//    @GET(BuildConfig.PUSH_INBOX_USER_CAMPAIGNS_PATH_AND_QUERY)
//    suspend fun getUserCampaignsList(@Query("browserId") browserId: String): Result<List<UserCampaignModel>>

//    @POST("/todos/create")
//    fun createTodo(@Body todo: Todo): Response<CreateTodoResponse>
