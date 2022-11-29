package com.example.todolist.entity

import com.example.todolist.Enums.LoginState
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User (

    @SerializedName("id")
    var userId: Int = 0,

    @SerializedName("username")
    var name: String = "",

    @SerializedName("lookup")
    var lookup: String = "",

    @SerializedName("email")
    var email: String = "",

    var password: String = "",

    var loginState: LoginState = LoginState.SIGNED_OFF,

    @SerializedName("friends")
    var friends: List<User> = ArrayList()
)
