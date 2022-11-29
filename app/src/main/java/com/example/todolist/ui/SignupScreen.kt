package com.example.todolist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.R
import com.example.todolist.ui.layout.components.LoginTextField
import com.example.todolist.ui.layout.components.PasswordField
import com.example.todolist.ui.viewModel.UserViewModel

@Composable
fun SignupScreen(
    userViewModel: UserViewModel = viewModel(),
    onSignup: (Int) -> Unit,
    onToLoginClick: () -> Unit
)
{
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Spacer(modifier = Modifier.height(8.dp))

        LoginTextField(
            name = userViewModel.loginnameInput,
            labelText = "Loginname",
            onInput = { userViewModel.updateLoginname(it) },
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        LoginTextField(
            name = userViewModel.usernameInput,
            labelText = "Username",
            onInput = { userViewModel.updateUsername(it) },
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        PasswordField(
            password = userViewModel.passwordInput,
            labelText = stringResource(R.string.login_label_password),
            onInput = { userViewModel.updatePassword(it) },
            onDone = {},
            onNext = {focusManager.moveFocus(FocusDirection.Down)},
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordField(
            password = userViewModel.passwordConfirmInput,
            labelText = stringResource(R.string.login_label_password_confirm),
            onInput = { userViewModel.updatePasswordConfirm(it) },
            onDone =  { userViewModel.attemptSignUp(onSignup) },
            onNext = {},
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { userViewModel.attemptSignUp(onSignup) }
            )
            {
                Text(text = stringResource(R.string.login_button_create))
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onToLoginClick() },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            )
            {
                Text(text = stringResource(R.string.login_button_to_login))
            }
        }
    }
}
