package com.tck.myapplication.presentation.screens.task

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.tck.myapplication.R
import com.tck.myapplication.domain.model.Priority
import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.presentation.screens.components.DisplayAlertDialog
import com.tck.myapplication.ui.theme.topAppBarBackgroundColor
import com.tck.myapplication.ui.theme.topAppBarContentColor

@Composable
fun TaskAppBar(
    selectedObject: ToDoTask?,
    onAddClicked: () -> Unit,
    onUpdateClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    if (selectedObject == null) {
        NewTaskAppBar(
            onAddClicked = onAddClicked,
            onBackClicked = onBackClicked
        )
    } else {
        ExistingTaskAppBar(
            selectedObject = selectedObject,
            onUpdateClicked = onUpdateClicked,
            onDeleteClicked = onDeleteClicked,
            onCloseClicked = onCloseClicked
        )
    }
}

@Composable
fun NewTaskAppBar(
    onAddClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = onBackClicked)
        },
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = onAddClicked)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: () -> Unit
) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: () -> Unit
) {
    IconButton(onClick = { onAddClicked() }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(
    selectedObject: ToDoTask,
    onUpdateClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = onCloseClicked)
        },
        title = {
            Text(
                text = selectedObject.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedObject,
                onUpdateClicked = onUpdateClicked,
                onDeleteClicked = onDeleteClicked
            )
        }
    )
}

@Composable
fun CloseAction(
    onCloseClicked: () -> Unit
) {
    IconButton(onClick = { onCloseClicked() }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    onUpdateClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(
            id = R.string.delete_task,
            selectedTask.title
        ),
        message = stringResource(
            id = R.string.delete_task_confirmation,
            selectedTask.title
        ),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { onDeleteClicked }
    )

    DeleteAction(onDeleteClicked = { openDialog = true })
    UpdateAction(onUpdateClicked = onUpdateClicked)
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: () -> Unit
) {
    IconButton(onClick = { onUpdateClicked() }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Composable
@Preview
private fun NewTaskAppBarPreview() {
    NewTaskAppBar(
        onAddClicked = {},
        onBackClicked = {}
    )
}

@Composable
@Preview
private fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        selectedObject = ToDoTask(
            id = 0,
            title = "some random title",
            description = "Some random text",
            priority = Priority.Low
        ),
        onDeleteClicked = {},
        onUpdateClicked = {},
        onCloseClicked = {}
    )
}