package com.tck.myapplication.presentation.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.tck.myapplication.domain.model.Priority
import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.presentation.viewmodels.SharedViewModel
import com.tck.myapplication.util.Action
import com.tck.myapplication.util.Constants

@Composable
fun TaskScreen(
    taskId: Int,
    navigateToListScreen: (Action) -> Unit,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val title: String by viewModel.title
    val description: String by viewModel.description
    val priority: Priority by viewModel.priority

    val context = LocalContext.current

    LaunchedEffect(key1 = taskId) {
        viewModel.getSelectedTask(taskId = taskId)
    }

    val selectedTask by viewModel.selectedTask.collectAsState()
    LaunchedEffect(key1 = selectedTask) {
        if (selectedTask != null || taskId == -1) {
            viewModel.updateTaskFields(selectedTask = selectedTask)
        }
    }
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (viewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = { padding ->
            TaskContent(
                modifier = Modifier.padding(padding),
                title = title,
                onTitleChange = {
                    viewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    viewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    viewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty.",
        Toast.LENGTH_SHORT
    ).show()
}