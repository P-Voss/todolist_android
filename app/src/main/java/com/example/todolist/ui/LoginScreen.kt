package com.example.todolist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.ui.theme.TodolistTheme
import com.example.todolist.R
import com.example.todolist.ui.viewModel.UserViewModel

@Composable
fun LoginScreen(
    userViewModel: UserViewModel = viewModel(),
    onLogin: (Int) -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(id = R.drawable.mainimage),
            contentDescription = "Get it Done!",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(32.dp))

        UsernameField(
            name = userViewModel.usernameInput,
            onInput = { userViewModel.updateUsername(it) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        PasswordField(
            password = userViewModel.passwordInput,
            onInput = { userViewModel.updatePassword(it) }
        ) { userViewModel.attemptLogin(onLogin) }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { userViewModel.attemptLogin(onLogin) }
        )
        {
            Text(text = stringResource(R.string.login_label_signIn))
        }
    }
}

@Composable
fun UsernameField(
    name: String,
    onInput: (String) -> Unit
)
{
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = name,
        onValueChange = onInput,
        singleLine = true,
        label = {
            Text(text = stringResource(R.string.login_label_name))
        },
        isError = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        )
    )
}

@Composable
fun PasswordField(
    password: String,
    onInput: (String) -> Unit,
    onDone: () -> Unit
)
{
    OutlinedTextField(
        value = password,
        onValueChange = onInput,
        singleLine = true,
        label = {
            Text(text = stringResource(R.string.login_label_password))
        },
        isError = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TodolistTheme {
        LoginScreen(onLogin = {})
    }
}