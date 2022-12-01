package com.example.todolist.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.entity.Task
import com.example.todolist.entity.User
import com.example.todolist.network.TodolistApi
import com.example.todolist.network.TodolistApiService
import com.example.todolist.network.request.FetchTasklistRequest
import com.example.todolist.network.request.RemoveTaskRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.await

private const val TAG = "TasklistViewModel"

class TasklistViewModel: ViewModel() {

    lateinit var todolistApiService: TodolistApiService


    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    private val _tasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun deleteTask(userId: Int, taskId: Int, callback: () -> Unit) {
        Log.d(TAG, "UserId: " + userId.toString())
        Log.d(TAG, "TaskId: " + taskId.toString())
        viewModelScope.launch {
            try {
                val payload = RemoveTaskRequest(
                    userId = userId,
                    taskId = taskId
                )

                Log.d("TasklistViewModel", "Attempting Request")
                val result = TodolistApi.retrofitService.removeTask(payload).await()
                Log.d("TasklistViewModel", "Executed Request")
                if (result.success) {
                    callback()
                }
            } catch (e: Exception) {
                Log.d("TasklistViewModel", "Caught Exception: " + e.message)
            }
        }
    }

    fun refreshTasklist(userId: Int) {
        viewModelScope.launch {
            try {
                val newTasks: MutableList<Task> = mutableListOf()
                val payload = FetchTasklistRequest(
                    userId = userId,
                )

                Log.d("TasklistViewModel", "Attempting Request")
                val result = TodolistApi.retrofitService.fetchTasks(payload).await()
                Log.d("TasklistViewModel", "Executed Request")
                if (result.success) {
                    for (task in result.tasks) {
                        newTasks.add(task)
                    }
                    _tasks.value = newTasks
                }

                Log.d("TasklistViewModel", result.toString())
            } catch (e: Exception) {
                Log.d("TasklistViewModel", "Caught Exception: " + e.message)
            }
        }
    }

}