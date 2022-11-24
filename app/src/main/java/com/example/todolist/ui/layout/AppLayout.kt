package com.example.todolist.ui.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.todolist.TodolistScreen


@Composable
fun AppLayout(
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    navBar: @Composable () -> Unit,
    navController: NavHostController,
    @StringRes buttonLabel: Int,
    navigationTarget: TodolistScreen
)
{
    Scaffold(
        bottomBar = navBar ,
        content = content,
        topBar = topBar,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(buttonLabel)) },
                onClick = {
                    navController.navigate(navigationTarget.name)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = MaterialTheme.colors.background
    )
}