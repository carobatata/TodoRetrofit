package com.example.practicinginterview.api

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    @GET("/todos")
    suspend fun getAllTodos(): Response<List<TodoModel>>

}

//    @GET(BuildConfig.PUSH_INBOX_USER_CAMPAIGNS_PATH_AND_QUERY)
//    suspend fun getUserCampaignsList(@Query("browserId") browserId: String): Result<List<UserCampaignModel>>

//    @POST("/todos/create")
//    fun createTodo(@Body todo: Todo): Response<CreateTodoResponse>
