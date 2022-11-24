package com.example.todolist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.Enums.Priority
import com.example.todolist.R
import com.example.todolist.entity.Task
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel

@Composable
fun ListScreen(
    userViewModel: UserViewModel,
    tasklistViewModel: TasklistViewModel
)
{
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = "Übersicht",
            style = MaterialTheme.typography.h2
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
        {
            items(tasklistViewModel.tasks) {
                    item: Task -> TaskCard(task = item)
            }
        }

    }
}

@Composable
fun TaskCard(task: Task) {
    Card (
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.h3
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = stringResource(R.string.label_open_calendar, task.getFormattedDueDate()),
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Priorität: " + getPriority(task.priority),
                    style = MaterialTheme.typography.body1
                )
            }
            Text(
                text = task.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth()
            )
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