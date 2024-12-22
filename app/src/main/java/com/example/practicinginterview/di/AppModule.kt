package com.example.practicinginterview.di

import TodoRepository
import com.example.practicinginterview.TodoViewModel
import com.example.practicinginterview.api.TodoApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//provide dependencies
val appModule = module {

    // Provide Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide API interface
    single {
        get<Retrofit>().create(TodoApi::class.java)
    }

    single {
        TodoRepository(get())
    }

    viewModel {
        TodoViewModel(get())
    }
}