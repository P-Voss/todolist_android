package com.example.todolist.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Enums.Priority
import com.example.todolist.entity.Task
import com.example.todolist.network.TodolistApi
import com.example.todolist.network.TodolistApiService
import com.example.todolist.network.request.AddTaskRequest
import com.example.todolist.network.request.FetchTasklistRequest
import kotlinx.coroutines.launch
import retrofit2.await
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "TasklistViewModel"

class TasklistViewModel: ViewModel() {

    lateinit var todolistApiService: TodolistApiService


    var tasks: MutableList<Task> = mutableListOf()
        private set


    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun refreshTasklist(userId: Int) {

        viewModelScope.launch {
            try {
                tasks = mutableListOf()
                val payload = FetchTasklistRequest(
                    userId = userId,
                )

                Log.d("TasklistViewModel", "Attempting Request")
                val result = TodolistApi.retrofitService.fetchTasks(payload).await()
                Log.d("TasklistViewModel", "Executed Request")
                if (result.success) {
                    for (task in result.tasks) {
                        addTask(task)
                    }
                }

                Log.d("TasklistViewModel", result.toString())
            } catch (e: Exception) {
                Log.d("TasklistViewModel", "Caught Exception: " + e.message)
            }
        }
    }

}