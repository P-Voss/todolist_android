package com.example.todolist.ui.theme.colors

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color



object GreenColors {
    val PrimaryColor = Color(0xFF5fc92d)
    val PrimaryLightColor = Color(0xFF95fd61)
    val PrimaryDarkColor = Color(0xFF209700)

    val SecondaryColor = Color(0xFF311b92)
    val SecondaryLightColor = Color(0xFF6746c3)
    val SecondaryDarkColor = Color(0xFF000063)

    val PrimaryTextColor = Color(0xFF000000)
    val SecondaryTextColor = Color(0xFFffffff)

    val ErrorSurfaceColor = Color(0xFFff4d4d)
    val ErrorPrimaryColor = Color(0xFFe60000)
    val ErrorSecondaryColor = Color(0xFFff3333)

    fun getMainPalette(): Colors {
        return lightColors(
            primary = PrimaryColor,
            primaryVariant = PrimaryLightColor,
            secondary = SecondaryColor,
            secondaryVariant = SecondaryLightColor,
            surface = PrimaryDarkColor,
            onSurface = PrimaryTextColor,
            onPrimary = PrimaryTextColor,
            onSecondary = SecondaryTextColor,
            background = Color.LightGray
        )
    }

    fun getErrorPalette(): Colors {
        return lightColors(
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
    }
}