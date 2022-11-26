package com.example.todolist.network.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
