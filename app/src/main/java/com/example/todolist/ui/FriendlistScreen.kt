package com.example.todolist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.entity.User
import com.example.todolist.ui.viewModel.UserViewModel

@Composable
fun FriendlistScreen(
    userViewModel: UserViewModel
)
{
    val user by userViewModel.user.collectAsState()
    val focusManager = LocalFocusManager.current
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = stringResource(R.string.headline_friendlist),
            style = MaterialTheme.typography.h1
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
            {

                Text(
                    text = "Dein Usercode: " + user.lookup,
                    style = MaterialTheme.typography.h2
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    TextField(
                        value = userViewModel.friendCodeInput,
                        label = {
                            Text(text = stringResource(R.string.friendlist_label_usercode))
                        },
                        onValueChange = {userViewModel.updateFriendCodeInput(it)},
                        modifier = Modifier.fillMaxWidth(0.5f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                userViewModel.attemptAddFriend()
                                focusManager.clearFocus()
                            }
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {
                        userViewModel.attemptAddFriend()
                        focusManager.clearFocus()
                    }) {
                        Text(text = stringResource(R.string.friendlist_button_add))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Fixed(2)
        )
        {
            items(user.friends) {
                    item -> FriendCard(user = item)
            }
        }

    }
}

@Composable
fun FriendCard(
    user: User,
) {
    Card (
        modifier = Modifier.fillMaxSize(),
    )
    {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h3
            )
            Text(
                text = user.lookup,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
