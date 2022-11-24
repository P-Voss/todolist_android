package com.example.todolist.ui.layout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.todolist.R

@Composable
fun TopBarLogin() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
//                Image(painter = painterResource(id = R.drawable.mainicon), contentDescription = "")
//                Text(stringResource(R.string.app_name))
            }
        }
    )
}