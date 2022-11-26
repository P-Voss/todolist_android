package com.example.todolist.network.request

data class DelegateTaskRequest(
    val userId: Int,
    val taskId: Int,
    val friendId: Int
)