package com.example.todolist.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.material.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todolist.R

// Set of Material typography styles to start with

val FiraSans = FontFamily(
    Font(R.font.fira_sans_regular)
)
val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FiraSans,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    h4 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.White.copy(alpha = 0.7f),
        shadow = Shadow(
            color = Color.Black,
            offset = Offset(x = 2f, y = 2f),
            blurRadius = 2f
        )
    )
)

val ErrorTypography = Typography(
    h1 = TextStyle(
        fontFamily = FiraSans,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White.copy(alpha = 0.8f),
        shadow = Shadow(
            color = Color.Black,
            offset = Offset(x = 2f, y = 2f),
            blurRadius = 2f
        )
    ),
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.White.copy(alpha = 0.75f),
        shadow = Shadow(
            color = Color.Black,
            offset = Offset(x = 1f, y = 1f),
            blurRadius = 1f
        )
    ),
    body2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        color = Color.White.copy(alpha = 0.75f),
        shadow = Shadow(
            color = Color.Black,
            offset = Offset(x = 1f, y = 1f),
            blurRadius = 1f
        )
    ),
)