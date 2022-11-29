package com.example.todolist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.todolist.ui.theme.colors.BlueColors
import com.example.todolist.ui.theme.colors.GreenColors


@Composable
fun TodolistTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = BlueColors.getMainPalette()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun ErrorTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = BlueColors.getErrorPalette()

    MaterialTheme(
        colors = colors,
        typography = ErrorTypography,
        shapes = Shapes,
        content = content
    )
}