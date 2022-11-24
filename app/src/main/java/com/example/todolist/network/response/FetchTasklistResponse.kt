package com.example.todolist.network.response

import com.example.todolist.entity.Task
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FetchTasklistResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("tasks")
    val tasks: List<Task>,
    @SerializedName("message")
val message: String
)
