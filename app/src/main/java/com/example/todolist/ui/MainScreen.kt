package com.example.todolist.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.entity.Task
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

    val currentContext = LocalContext.current

    val datePicker = getDatePicker(currentContext, createTaskViewModel)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        Card {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Text(
                    text = stringResource(R.string.create_task_headline),
                    style = MaterialTheme.typography.h1
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = createTaskViewModel.titleInput,
                    singleLine = true,
                    onValueChange = {createTaskViewModel.setTitle(it)},
                    label = { Text(text = stringResource(R.string.task_title))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = createTaskViewModel.descriptionInput,
                    onValueChange = {createTaskViewModel.setDescription(it)},
                    label = { Text(text = stringResource(R.string.task_description))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )

                Row {
                    Button(
                        onClick = { datePicker.show() },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(12.dp)
                    )
                    {
                        Text(text = stringResource(R.string.label_open_calendar, createTaskViewModel.getFormattedDate()))
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        createTaskViewModel.saveTask(user){tasklistViewModel.refreshTasklist(user.userId)}
                    }
                )
                {
                    Text(text = stringResource(R.string.label_task_create))
                }
            }
        }
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
    // Initializing a Calendar
    val calendar = Calendar.getInstance()

    // Fetching current year, month and day
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

                Log.d("DatePicker", "formatted Date: " + dateString)

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

@Preview
@Composable
fun displayPreview() {
    MainScreen(
        userViewModel = UserViewModel(),
        createTaskViewModel = CreateTaskViewModel(),
        tasklistViewModel = TasklistViewModel()
    )
}