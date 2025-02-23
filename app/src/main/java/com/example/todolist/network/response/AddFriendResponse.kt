package com.example.todolist.network.response

import com.example.todolist.entity.User
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AddFriendResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("friends")
    var friends: List<User>,
    @SerializedName("message")
    var message: String,
)