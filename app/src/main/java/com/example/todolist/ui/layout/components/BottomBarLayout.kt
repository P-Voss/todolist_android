package com.example.todolist.ui.layout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarLayout() {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .padding(12.dp)
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Von P.Voss",
            color = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "für IWMB01",
            color = MaterialTheme.colors.onPrimary
        )
    }
}