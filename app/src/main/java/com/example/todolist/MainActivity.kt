package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.Enums.LoginState
import com.example.todolist.network.TodolistApi
import com.example.todolist.ui.LoginScreen
import com.example.todolist.ui.theme.TodolistTheme
import com.example.todolist.ui.viewModel.CreateTaskViewModel
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel

private const val TAG = "MainActivity"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        val userViewModel: UserViewModel by viewModels()
        val createTaskViewModel: CreateTaskViewModel by viewModels()
        val tasklistViewModel: TasklistViewModel by viewModels()

        Log.d(TAG, "isLoggedIn: " + userViewModel.isLoggedIn().toString())
        Log.d(TAG, "username: " + userViewModel.usernameInput)

        setContent {
            TodolistTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TodolistApp(
                        userViewModel = userViewModel,
                        createTaskViewModel = createTaskViewModel,
                        tasklistViewModel = tasklistViewModel
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }


    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}

