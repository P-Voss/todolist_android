package com.example.todolist.network.request

data class AddFriendRequest(
    val userId: Int,
    val lookup: String,
)