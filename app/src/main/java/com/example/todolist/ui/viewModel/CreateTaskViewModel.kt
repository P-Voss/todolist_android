package com.example.todolist.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.entity.Task
import com.example.todolist.Enums.CreationState
import com.example.todolist.Enums.Priority
import com.example.todolist.entity.User
import com.example.todolist.network.TodolistApi
import com.example.todolist.network.TodolistApiService
import com.example.todolist.network.request.AddTaskRequest
import com.example.todolist.network.request.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.await
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
import javax.security.auth.callback.Callback

private const val TAG = "CreateTaskViewModel"

class CreateTaskViewModel : ViewModel() {

    private val _creationState = MutableStateFlow(CreationState.OPEN)
    val creationState: StateFlow<CreationState> = _creationState.asStateFlow()

    var friendId by mutableStateOf(0)
    var friendDisplay by mutableStateOf("Für mich")

    var priorityInput by mutableStateOf(Priority.MEDIUM)

    var titleInput by mutableStateOf("")
        private set
    var dateInput by mutableStateOf(Date())
        private set
    var descriptionInput by mutableStateOf("")
        private set

    fun getTask(): Task {
        return Task(
            id = null,
            title = titleInput,
            description = descriptionInput,
            dueDate = dateInput,
            priority = Priority.MEDIUM
        )
    }

    fun updateFriend(userId: Int, display: String) {
        friendId = userId
        friendDisplay = display
    }

    fun updatePriority(priority: Priority) {
        priorityInput = priority
    }

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

    fun saveTask(user: User, callback: () -> Unit) {
        if (titleInput == "") {
            return
        }

        Log.d("CreateTaskViewModel", "FriendId: " + friendId.toString())
        Log.d("CreateTaskViewModel", "UserId: " + user.userId.toString())
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