package com.example.todolist.network.request

data class FetchTasklistRequest(val userId: Int, val sortString: String = "Name_ASC")
