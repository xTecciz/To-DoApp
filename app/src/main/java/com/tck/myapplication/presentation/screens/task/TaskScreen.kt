package com.tck.myapplication.presentation.screens.task

import android.content.Context
import android.util.Log
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
import androidx.navigation.NavHostController
import com.tck.myapplication.domain.model.Priority
import com.tck.myapplication.presentation.viewmodels.DeleteViewModel
import com.tck.myapplication.presentation.viewmodels.TaskViewModel

@Composable
fun TaskScreen(
    taskId: Int?,
    navHostController: NavHostController,
    viewModel: TaskViewModel = hiltViewModel(),
    deleteViewModel: DeleteViewModel = hiltViewModel()
) {
    val title: String by viewModel.title
    val description: String by viewModel.description
    val priority: Priority by viewModel.priority

    val context = LocalContext.current

    LaunchedEffect(key1 = taskId) {
        Log.d("TAGONE","taskId taskScreen $taskId")
        if (taskId != null && taskId != -1)
        viewModel.getSelectedTask(taskId)
    }

    val selectedTask by viewModel.selectedTask.collectAsState()
    LaunchedEffect(key1 = selectedTask) {
        if (selectedTask != null || taskId == null) {
            viewModel.updateTaskFields(selectedTask = selectedTask)
        }
    }
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedObject = selectedTask,
                onAddClicked = {
                    if (viewModel.validateFields()) {
                        displayToast(context = context)
                    } else {
                        viewModel.addTask()
                        navHostController.popBackStack()
                    }
                },
                onUpdateClicked = {
                    if (viewModel.validateFields()) {
                        displayToast(context = context)
                    } else {
                        viewModel.updateTask()
                        navHostController.popBackStack()
                    }
                },
                onDeleteClicked = {
                    Log.d("TAGONE","${selectedTask?.id}")
                    deleteViewModel.deleteTask(selectedTask?.id!!)
                    navHostController.popBackStack()
                },
                onBackClicked = { navHostController.popBackStack() },
                onCloseClicked = { navHostController.popBackStack() }
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