package com.example.todolist.network.request

data class RemoveTaskRequest(
    val userId: Int,
    val taskId: Int
)