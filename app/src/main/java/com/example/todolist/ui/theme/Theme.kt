package com.example.todolist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.todolist.R

private val DarkColorPalette = darkColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryDarkColor,
    surface = SecondaryDarkColor,
    onSurface = SecondaryTextColor,
    onPrimary = SecondaryTextColor,
    onSecondary = PrimaryTextColor
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryLightColor,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryLightColor,
    surface = PrimaryDarkColor,
    onSurface = PrimaryTextColor,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    background = Color.LightGray
//    surface = SecondaryLightColor,
//    onSurface = SecondaryTextColor,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val ErrorColorPalette = lightColors(
    primary = ErrorPrimaryColor,
    primaryVariant = PrimaryLightColor,
    secondary = ErrorSecondaryColor,
    secondaryVariant = SecondaryLightColor,
    surface = ErrorSurfaceColor,
    onSurface = PrimaryTextColor,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    background = Color.LightGray
)

@Composable
fun TodolistTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun ErrorTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = ErrorColorPalette

    MaterialTheme(
        colors = colors,
        typography = ErrorTypography,
        shapes = Shapes,
        content = content
    )
}