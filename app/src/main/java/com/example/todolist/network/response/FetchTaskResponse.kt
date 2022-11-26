package com.example.todolist.network.response

import com.example.todolist.entity.Task
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FetchTaskResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("task")
    val task: Task
)
