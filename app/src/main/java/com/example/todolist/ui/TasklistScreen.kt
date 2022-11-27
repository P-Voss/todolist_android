package com.example.todolist.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.Enums.Priority
import com.example.todolist.R
import com.example.todolist.entity.Task
import com.example.todolist.ui.theme.ErrorTheme
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel
import java.util.Date

@Composable
fun TasklistScreen(
    userViewModel: UserViewModel,
    tasklistViewModel: TasklistViewModel
)
{
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = stringResource(R.string.headline_todolist),
            style = MaterialTheme.typography.h1
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
        {
            items(tasklistViewModel.tasks) {
                    item: Task -> ListEntry(task = item)
            }
        }
    }
}

@Composable
fun ListEntry(
    task: Task,
    modifier: Modifier = Modifier
) {
    val currentDate = Date()
    val isOnTime = task.dueDate?.after(currentDate)

    if (isOnTime != true) {
        Log.d("ListScreen", "Using Error Theme")
        ErrorTheme {
            var backgroundColor = MaterialTheme.colors.surface
            if (task.priority.name == Priority.HIGHEST.name) {
                backgroundColor = MaterialTheme.colors.primary
            }
            if (task.priority.name == Priority.HIGH.name) {
                backgroundColor = MaterialTheme.colors.secondary
            }
            TaskCard(task = task, backgroundColor = backgroundColor)
        }
    } else {
        Log.d("ListScreen", "Using Default Theme")
        TaskCard(task = task)
    }

}

@Composable
fun TaskCard(
    task: Task,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    Card (
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = backgroundColor
    )
    {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.label_created_date, task.getFormattedCreationDate()),
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = stringResource(R.string.label_open_calendar, task.getFormattedDueDate()),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Priorität: " + getPriority(task.priority),
                    style = MaterialTheme.typography.body1
                )
            }
            if(task.description != "") {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

fun getPriority(priority: Priority): String {
    return when(priority) {
        Priority.LOW -> "Niedrig"
        Priority.MEDIUM -> "Mittel"
        Priority.HIGH -> "Hoch"
        Priority.HIGHEST -> "Höchste"
        else -> "Keine"
    }
}