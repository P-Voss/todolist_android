package com.example.todolist.ui.layout.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.todolist.R


@Composable
fun TopBarLayout(
    onLogout: () -> Unit,
    onToFriendlist: () -> Unit,
    modifier: Modifier = Modifier
)
{
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
//                Image(
//                    painter = painterResource(id = R.drawable.mainicon),
//                    contentDescription = "",
//                    contentScale = ContentScale.Fit
//                )
                Button(onClick = { onToFriendlist() }) {
                    Row {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                        Icon(Icons.Filled.Face, "A Friend")
                    }
                }

                Spacer(modifier = Modifier.weight(.5f))

                Text(stringResource(R.string.app_name))

                Spacer(modifier = Modifier.weight(.5f))

                Button(
                    onClick = { onLogout() },
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