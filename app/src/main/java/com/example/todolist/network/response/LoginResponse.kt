package com.example.todolist.network.response

import com.example.todolist.entity.User
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("user")
    var user: User,
    @SerializedName("message")
    var message: String,
)