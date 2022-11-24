package com.example.todolist.network.request

data class AddTaskRequest(
    val userId: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val creationDate: String,
    val priority: String
)