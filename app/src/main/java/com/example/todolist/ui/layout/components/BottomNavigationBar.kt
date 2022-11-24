package com.example.todolist.ui.layout.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.todolist.R
import com.example.todolist.TodolistScreen


@Composable
fun BottomNavigationBar(
    currentScreen: TodolistScreen,
    navController: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        BottomNavigationItem(
            selected = currentScreen.name == TodolistScreen.Main.name,
            onClick = { navController.navigate(TodolistScreen.Main.name) },
            label = { Text(text = stringResource(R.string.navigation_label_main)) },
            icon = { Icons.Filled.Create }
        )
        BottomNavigationItem(
            selected = currentScreen.name == TodolistScreen.List.name,
            onClick = { navController.navigate(TodolistScreen.List.name) },
            label = { Text(text = stringResource(R.string.navigation_label_list)) },
            icon = { Icons.Filled.List }
        )
    }
}