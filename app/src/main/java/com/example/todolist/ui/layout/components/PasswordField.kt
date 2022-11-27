package com.example.todolist.ui.layout.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.todolist.R


@Composable
fun PasswordField(
    password: String,
    onInput: (String) -> Unit,
    onDone: () -> Unit,
    onNext: () -> Unit,
    labelText: String,
    imeAction: ImeAction
)
{
    OutlinedTextField(
        value = password,
        onValueChange = onInput,
        singleLine = true,
        label = {
            Text(text = labelText)
        },
        isError = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() },
            onNext = { onNext() }
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}