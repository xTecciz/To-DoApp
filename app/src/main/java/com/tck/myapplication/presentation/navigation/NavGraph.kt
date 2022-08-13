package com.tck.myapplication.presentation.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tck.myapplication.presentation.screens.list.ListScreen
import com.tck.myapplication.presentation.screens.splash.SplashScreen
import com.tck.myapplication.presentation.screens.task.TaskScreen
import com.tck.myapplication.util.Constants.LIST_ARGUMENT_KEY
import com.tck.myapplication.util.Constants.TASK_ARGUMENT_KEY
import com.tck.myapplication.util.toAction


@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navigateToListScreen = { navHostController.navigate(Screen.List.route) })
        }

        composable(
            route = Screen.List.route,
            arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { NavBackStackEntry ->
            val action = NavBackStackEntry.arguments!!.getString(TASK_ARGUMENT_KEY).toAction()
            ListScreen(
                action = action,
                navigateToTaskScreen = { navHostController.navigate(Screen.Task.passId(it)) }
            )
        }

        composable(
            route = Screen.Task.route,
            arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { NavBackStackEntry ->
            val taskId = NavBackStackEntry.arguments!!.getInt(LIST_ARGUMENT_KEY)
            TaskScreen(
                taskId = taskId,
                navigateToListScreen = { navHostController.navigate(Screen.List.passListAction(it.toString())) }
            )
        }
    }
}