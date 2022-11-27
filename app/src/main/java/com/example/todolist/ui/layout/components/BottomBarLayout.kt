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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R

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
            text = stringResource(R.string.text_bottom_bar_left),
            color = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.text_bottom_bar_right),
            color = MaterialTheme.colors.onPrimary
        )
    }
}