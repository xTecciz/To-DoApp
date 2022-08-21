@file:OptIn(ExperimentalAnimationApi::class)

package com.tck.myapplication.presentation.screens.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tck.myapplication.R
import com.tck.myapplication.presentation.navigation.Screen
import com.tck.myapplication.presentation.viewmodels.DeleteViewModel
import com.tck.myapplication.presentation.viewmodels.ListViewModel
import com.tck.myapplication.ui.theme.fabBackgroundColor
import com.tck.myapplication.util.SearchAppBarState

@Composable
fun ListScreen(
    navHostController: NavHostController,
    viewModel: ListViewModel = hiltViewModel(),
    deleteViewModel: DeleteViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllTasks()
        viewModel.readSortState()
    }

    val allTasks by viewModel.allTasks.collectAsState()
    val searchedTasks by viewModel.searchedTasks.collectAsState()
    val sortState by viewModel.sortState.collectAsState()
    val lowPriorityTasks by viewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by viewModel.highPriorityTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by viewModel.searchAppBarState
    val searchTextState: String by viewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                viewModel = viewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = { padding ->
            ListContent(
                modifier = Modifier.padding(padding),
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                searchAppBarState = searchAppBarState,
                onSwipeToDelete = {
                    deleteViewModel.deleteTask(it)
                },
                navigateToTaskScreen = {
                    navHostController.navigate(Screen.Task.passId(it))
                }
            )
        },
        floatingActionButton = {
            ListFab { navHostController.navigate(Screen.Task.passId(-1)) }
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked()
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(
                id = R.string.add_button
            ),
            tint = Color.White
        )
    }
}

//    DisplaySnackBar(
//        scaffoldState = scaffoldState,
//        handleDatabaseActions = { viewModel.handleDatabaseActions(action = action) },
//        onUndoClicked = { viewModel.action.value = it },
//        taskTitle = viewModel.title.value,
//        action = action
//    )

//@Composable
//fun DisplaySnackBar(
//    scaffoldState: ScaffoldState,
//    handleDatabaseActions: () -> Unit,
//    onUndoClicked: (Action) -> Unit,
//    taskTitle: String,
//    action: Action
//) {
//    handleDatabaseActions()
//
//    val scope = rememberCoroutineScope()
//    LaunchedEffect(key1 = action) {
//        if (action != Action.NO_ACTION) {
//            scope.launch {
//                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
//                    message = setMessage(action = action, taskTitle = taskTitle),
//                    actionLabel = setActionLabel(action = action)
//                )
//                undoDeletedTask(
//                    action = action,
//                    snackBarResult = snackBarResult,
//                    onUndoClicked = onUndoClicked
//                )
//            }
//        }
//    }
//}
//
//private fun setMessage(action: Action, taskTitle: String): String {
//    return when (action) {
//        Action.DELETE_ALL -> "All Tasks Removed."
//        else -> "${action.name}: $taskTitle"
//    }
//}
//
//private fun setActionLabel(action: Action): String {
//    return if (action.name == "DELETE") {
//        "UNDO"
//    } else {
//        "OK"
//    }
//}
//
//private fun undoDeletedTask(
//    action: Action,
//    snackBarResult: SnackbarResult,
//    onUndoClicked: (Action) -> Unit
//) {
//    if (snackBarResult == SnackbarResult.ActionPerformed
//        && action == Action.DELETE
//    ) {
//        onUndoClicked(Action.UNDO)
//    }
//}