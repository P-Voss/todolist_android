package com.example.todolist.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.todolist.Enums.Priority
import com.example.todolist.R
import com.example.todolist.entity.Task
import com.example.todolist.ui.theme.ErrorTheme
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.math.roundToInt


@Composable
fun TasklistScreen(
    userViewModel: UserViewModel,
    tasklistViewModel: TasklistViewModel
)
{
    val tasks by tasklistViewModel.tasks.collectAsState()

    val context = LocalContext.current
    val toast = Toast.makeText(context, stringResource(R.string.tasklist_ondelete_toast), Toast.LENGTH_SHORT)
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
        Text (
            text = stringResource(R.string.tasklist_delete_instructions),
            style = MaterialTheme.typography.h3
        )
        Text (
            text = stringResource(R.string.tasklist_expand_instructions),
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
        {
            items(tasks) {
                item: Task -> ListEntry(
                    task = item,
                    onDelete = {
                        tasklistViewModel.deleteTask(
                            userId = userViewModel.user.value.userId,
                            taskId = item.id ?: 0
                        ) { tasklistViewModel.refreshTasklist(userViewModel.user.value.userId) }
                        toast.show()
                    }
                )
            }
        }
    }
}

@Composable
fun ListEntry(
    task: Task,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentDate = Date()
    val isOnTime = task.dueDate?.after(currentDate)

    if (isOnTime != true) {
        ErrorTheme {
            var backgroundColor = MaterialTheme.colors.surface
            if (task.priority.name == Priority.HIGHEST.name) {
                backgroundColor = MaterialTheme.colors.primary
            }
            if (task.priority.name == Priority.HIGH.name) {
                backgroundColor = MaterialTheme.colors.secondary
            }
            SwipeableTaskCard(task = task, backgroundColor = backgroundColor, onDelete = onDelete)
        }
    } else {
        SwipeableTaskCard(task = task, onDelete = onDelete)
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableTaskCard(
    task: Task,
    onDelete: () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val rowWidth = configuration.screenWidthDp.dp * 0.99f

    val anchorDistance = 40.dp
    val swipeableState = rememberSwipeableState(0)

    val coroutineScope = rememberCoroutineScope()

    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                if (swipeableState.currentValue == 1) {
                    Log.d("Swipe", "completed swipe right")
                    onDelete()
                    coroutineScope.launch { swipeableState.animateTo(0) }
                }
            }
        }
    }

    val sizePx = with(LocalDensity.current) { anchorDistance.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Box(
        modifier = Modifier
            .width(screenWidth)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Horizontal,
                interactionSource = MutableInteractionSource()
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .width(rowWidth)
            ) {
                TaskCard(task = task, backgroundColor = backgroundColor)
            }
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
            )
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = backgroundColor
    )
    {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable { expanded = !expanded }
        ) {

            TaskHeader(task = task)
            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(visible = expanded) {
                TaskDetails(task = task)
                Spacer(modifier = Modifier.height(5.dp))
            }

            TaskFooter(task = task)
        }
    }
}

@Composable
fun TaskHeader(task: Task) {
    Column(
        modifier = Modifier.fillMaxWidth()
    )
    {
        Text(
            text = task.title,
            style = MaterialTheme.typography.h3
        )
        Text(
            text = "Priorität: " + getPriority(task.priority),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun TaskDetails(task: Task) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = stringResource(R.string.task_creation_details, task.getFormattedCreationDate(), task.creatorName),
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

@Composable
fun TaskFooter(task: Task) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    )
    {
        Text(
            text = stringResource(R.string.label_open_calendar, task.getFormattedDueDate()),
            style = MaterialTheme.typography.body1
        )
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