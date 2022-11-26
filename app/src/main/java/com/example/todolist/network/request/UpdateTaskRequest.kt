package com.example.todolist.network.request

data class UpdateTaskRequest(
    val userId: Int,
    val taskId: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val priority: String
)