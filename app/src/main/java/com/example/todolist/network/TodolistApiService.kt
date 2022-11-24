package com.example.todolist.network

import com.example.todolist.network.request.AddTaskRequest
import com.example.todolist.network.request.FetchTasklistRequest
import com.example.todolist.network.request.LoginRequest
import com.example.todolist.network.response.AddTaskResponse
import com.example.todolist.network.response.FetchTasklistResponse
import com.example.todolist.network.response.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "https://test-pv.de"

private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.HEADERS }

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface TodolistApiService {

    @POST("login")
    fun login(@Body payload: LoginRequest): Call<LoginResponse>

    @POST("addtask")
    fun addTask(@Body payload: AddTaskRequest): Call<AddTaskResponse>

    @POST("tasks")
    fun fetchTasks(@Body payload: FetchTasklistRequest): Call<FetchTasklistResponse>
}

object TodolistApi {

    val retrofitService: TodolistApiService by lazy {
        retrofit.create(TodolistApiService::class.java)
    }

}