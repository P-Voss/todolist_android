package com.example.todolist.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.entity.User
import com.example.todolist.Enums.LoginState
import com.example.todolist.network.request.LoginRequest
import com.example.todolist.network.TodolistApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.await

private const val TAG = "UserViewModel"

class UserViewModel() : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState.SIGNED_OFF)
    private var loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()


    var usernameInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set

    fun isLoggedIn(): Boolean {
        return user.value.loginState === LoginState.SIGNED_IN
    }

    fun updateUsername(name: String) {
        usernameInput = name
    }

    fun updatePassword(password: String) {
        passwordInput = password
    }


    fun attemptLogin(onLogin: (Int) -> Unit) {

        if (usernameInput == "" || passwordInput == "") {
            return
        }

        val payload = LoginRequest(
            email = usernameInput,
            password = passwordInput
        )

        viewModelScope.launch {
            try {
                Log.d("UserViewModel", "Attempting Request")
                val result = TodolistApi.retrofitService.login(payload).await()
                Log.d("UserViewModel", "Executed Request")

                Log.d("UserViewModel", result.toString())
                if (result.success) {
                    _user.update { currentState ->
                        currentState.copy(
                            name = result.user.name,
                            userId = result.user.userId,
                            email = result.user.email,
                            lookup = result.user.lookup,
                            loginState = LoginState.SIGNED_IN,
                            password = "",
                            friends = result.user.friends
                        )
                    }
                    onLogin(result.user.userId)
                    usernameInput = ""
                    passwordInput = ""
                }
            } catch (e: Exception) {
                Log.d("UserViewModel", "Caught Exception: " + e.message)
            }
        }
    }

    fun logout() {
        _user.update { currentState ->
            currentState.copy(
                userId = 0,
                name = "",
                password = "",
                loginState = LoginState.SIGNED_OFF
            )
        }
    }

}