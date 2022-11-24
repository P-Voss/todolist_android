package com.example.todolist.ui.layout.components

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.todolist.TodolistScreen
import com.example.todolist.ui.viewModel.UserViewModel
import com.example.todolist.R


@Composable
fun TopBarLayout(
    userViewModel: UserViewModel,
    currentScreen: TodolistScreen,
    canNavigateBack: Boolean,
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier
)
{
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.mainicon),
//                    painter = painterResource(id = R.drawable.topbaricon),
                    contentDescription = "test",
                    contentScale = ContentScale.Fit
                )

                Text(stringResource(R.string.app_name))

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { userViewModel.logout() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
                ) {
                    Text(
                        text = stringResource(R.string.label_logout),
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }
        },
        modifier = modifier,
    )
}