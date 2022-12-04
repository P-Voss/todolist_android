package com.example.todolist.ui.layout.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

/**
 * Workaround, Bug in Jetpack Compose?
 * Übernimmt ImeAction von erstem Passwortfeld, Klick-Reihenfolge entscheidet
 * welcher Button angezeigt wird.
 */
@Composable
fun PasswordConfirmField(
    password: String,
    onInput: (String) -> Unit,
    onDone: () -> Unit,
    labelText: String
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
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}
