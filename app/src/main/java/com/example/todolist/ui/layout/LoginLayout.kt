package com.example.todolist.ui.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.todolist.R
import com.example.todolist.TodolistScreen


@Composable
fun LoginLayout(
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    navBar: @Composable () -> Unit
)
{
    Scaffold(
        bottomBar = navBar ,
        content = content,
        topBar = topBar,
        backgroundColor = Color.White
    )
}