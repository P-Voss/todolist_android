package com.example.todolist.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import com.example.todolist.Enums.Priority
import com.example.todolist.R
import com.example.todolist.ui.viewModel.CreateTaskViewModel
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MainScreen(
    userViewModel: UserViewModel,
    createTaskViewModel: CreateTaskViewModel,
    tasklistViewModel: TasklistViewModel
)
{
    val user by userViewModel.user.collectAsState()
    val focusManager = LocalFocusManager.current

    val currentContext = LocalContext.current
    val toast = Toast.makeText(currentContext, stringResource(R.string.task_oncreate_toast), Toast.LENGTH_SHORT)

    val datePicker = getDatePicker(currentContext, createTaskViewModel)
    var expandFriendlist by remember { mutableStateOf(false) }
    var expandPriorities by remember { mutableStateOf(false) }

    val submitTask = {
        createTaskViewModel.saveTask(user){
            tasklistViewModel.refreshTasklist(user.userId)
            toast.show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(16.dp)
    )
    {
        Text(
            text = stringResource(R.string.create_task_headline),
            style = MaterialTheme.typography.h1
        )

        Card {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
            {
                Button(
                    onClick = { datePicker.show() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
                {
                    Text(text = stringResource(R.string.label_open_calendar, createTaskViewModel.getFormattedDate()))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box {
                    TextField(
                        value = createTaskViewModel.friendDisplay,
                        readOnly = true,
                        onValueChange = {},
                        label = { Text(text = "Für wen?")},
                        trailingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = "", Modifier.clickable { expandFriendlist = !expandFriendlist })},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                    DropdownMenu(
                        expanded = expandFriendlist,
                        onDismissRequest = { expandFriendlist = false },
                        modifier = Modifier.fillMaxWidth(),
                        offset = DpOffset(x = 0.dp, y = 0.dp),
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                createTaskViewModel.updateFriend(user.userId, "Für mich")
                                expandFriendlist = false
                            },
                            modifier = Modifier
                                .background(color = MaterialTheme.colors.primary)
                                .fillMaxWidth()
                                .padding(12.dp),
                        ) {
                            Text(text = "Für mich")
                        }
                        for (friend in user.friends) {
                            DropdownMenuItem(
                                onClick = {
                                    createTaskViewModel.updateFriend(friend.userId, friend.name + " - " + friend.lookup)
                                    expandFriendlist = false
                                },
                                modifier = Modifier
                                    .background(color = MaterialTheme.colors.primary)
                                    .padding(12.dp),
                            ) {
                                Text(text = "Für " + friend.name + " - " + friend.lookup)
                            }
                        }
                    }
                }


                Box {
                    TextField(
                        value = priorityName(createTaskViewModel.priorityInput),
                        readOnly = true,
                        onValueChange = {},
                        label = { Text(text = "Priorität?")},
                        trailingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = "", Modifier.clickable { expandPriorities = !expandPriorities })},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                    DropdownMenu(
                        expanded = expandPriorities,
                        onDismissRequest = { expandPriorities = false },
                        modifier = Modifier.fillMaxWidth(),
                        offset = DpOffset(x = 0.dp, y = 0.dp),
                    ) {
                        for (priority in Priority.values()) {
                            DropdownMenuItem(
                                onClick = {
                                    createTaskViewModel.updatePriority(priority)
                                    expandPriorities = false
                                },
                                modifier = Modifier
                                    .background(color = MaterialTheme.colors.primary)
                                    .padding(12.dp),
                            ) {
                                Text(text = priorityName(priority))
                            }
                        }
                    }
                }

                TextField(
                    value = createTaskViewModel.titleInput,
                    singleLine = true,
                    onValueChange = {createTaskViewModel.setTitle(it)},
                    label = { Text(text = stringResource(R.string.task_title))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = createTaskViewModel.descriptionInput,
                    onValueChange = {createTaskViewModel.setDescription(it)},
                    label = { Text(text = stringResource(R.string.task_description))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { submitTask() }
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { submitTask() }
                )
                {
                    Text(text = stringResource(R.string.label_task_create))
                }
            }
        }
    }
}

fun priorityName(priority: Priority): String {
    return when(priority) {
        Priority.LOW -> "Niedrig"
        Priority.MEDIUM -> "Mittel"
        Priority.HIGH -> "Hoch"
        Priority.HIGHEST -> "Höchste Priorität"
        else -> "Keine"
    }
}

/**
 * https://www.geeksforgeeks.org/date-picker-in-android-using-jetpack-compose/
 */
@SuppressLint("SimpleDateFormat")
fun getDatePicker(
    context: Context,
    createTaskViewModel: CreateTaskViewModel
): DatePickerDialog
{
    val calendar = Calendar.getInstance()

    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val dateString = "$mDayOfMonth/${mMonth+1}/$mYear"
            try {
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                val date = formatter.parse(dateString)

                createTaskViewModel.setDate(date)
            } catch (ex: ParseException) {
                Log.d("MainScreen", "Could not parse datestring: " + dateString)
            }
        },
        currentYear,
        currentMonth,
        currentDay
    )

    return mDatePickerDialog
}
