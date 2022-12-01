package com.example.todolist.ui.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Enums.Priority
import com.example.todolist.entity.User
import com.example.todolist.network.TodolistApi
import com.example.todolist.network.request.AddTaskRequest
import kotlinx.coroutines.launch
import retrofit2.await
import java.text.SimpleDateFormat
import java.util.Date


class CreateTaskViewModel : ViewModel() {

    var friendId by mutableStateOf(0)
    var friendDisplay by mutableStateOf("Für mich")

    var priorityInput by mutableStateOf(Priority.MEDIUM)

    var titleInput by mutableStateOf("")
        private set
    var dateInput by mutableStateOf(Date())
        private set
    var descriptionInput by mutableStateOf("")
        private set

    fun updateFriend(userId: Int, display: String) {
        friendId = userId
        friendDisplay = display
    }

    fun updatePriority(priority: Priority) {
        priorityInput = priority
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        return dateFormat.format(dateInput)
    }

    fun setDate(date: Date) {
        dateInput = date
    }

    fun setTitle(value: String) {
        titleInput = value
    }

    fun setDescription(value: String) {
        descriptionInput = value
    }

    @SuppressLint("SimpleDateFormat")
    fun saveTask(user: User, callback: () -> Unit) {
        if (titleInput == "") {
            return
        }

        viewModelScope.launch {
            try {
                val dateFormat = SimpleDateFormat("dd.MM.yyyy")
                val payload = AddTaskRequest(
                    userId = user.userId,
                    friendId = friendId,
                    title = titleInput,
                    description = descriptionInput,
                    dueDate = dateFormat.format(dateInput),
                    creationDate = dateFormat.format(Date()),
                    priority = priorityInput.name
                )

                Log.d("CreateTaskViewModel", "Attempting Request")
                val result = TodolistApi.retrofitService.addTask(payload).await()
                Log.d("CreateTaskViewModel", "Executed Request")

                Log.d("CreateTaskViewModel", result.toString())
                if (result.success) {
                    callback()

                    titleInput = ""
                    descriptionInput = ""
                    dateInput = Date()
                    priorityInput = Priority.MEDIUM
                    friendId = 0
                    friendDisplay = "Für mich"
                }
            } catch (e: Exception) {
                Log.d("CreateTaskViewModel", "Caught Exception: " + e.message)
            }
        }
    }

}