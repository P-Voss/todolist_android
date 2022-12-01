package com.example.todolist.ui.layout.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction


@Composable
fun LoginTextField(
    name: String,
    labelText: String,
    onInput: (String) -> Unit,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions
)
{
    OutlinedTextField(
        value = name,
        onValueChange = onInput,
        singleLine = true,
        label = {
            Text(text = labelText)
        },
        isError = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        modifier = Modifier.fillMaxWidth()
    )
}