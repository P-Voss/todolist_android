package com.example.todolist.network

import com.example.todolist.network.request.*
import com.example.todolist.network.response.*
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

    /*
    User Requests
     */

    @POST("login")
    fun login(@Body payload: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun createAccount(@Body payload: CreateAccountRequest): Call<LoginResponse>

    @POST("addfriend")
    fun addFriend(@Body payload: AddFriendRequest): Call<AddFriendResponse>

    /*
    Task Requests
     */

    @POST("addtask")
    fun addTask(@Body payload: AddTaskRequest): Call<FeedbackResponse>

    @POST("updatetask")
    fun updateTask(@Body payload: UpdateTaskRequest): Call<FeedbackResponse>

    @POST("delegate")
    fun delegateTask(@Body payload: DelegateTaskRequest): Call<FetchTasklistResponse>

    @POST("task")
    fun fetchTask(@Body payload: FetchTaskRequest): Call<FetchTaskResponse>

    @POST("tasks")
    fun fetchTasks(@Body payload: FetchTasklistRequest): Call<FetchTasklistResponse>
}

object TodolistApi {

    val retrofitService: TodolistApiService by lazy {
        retrofit.create(TodolistApiService::class.java)
    }

}