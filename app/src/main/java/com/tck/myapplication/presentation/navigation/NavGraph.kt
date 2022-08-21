package com.tck.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tck.myapplication.presentation.screens.list.ListScreen
import com.tck.myapplication.presentation.screens.splash.SplashScreen
import com.tck.myapplication.presentation.screens.task.TaskScreen
import com.tck.myapplication.util.Constants.TASK_ARGUMENT_KEY

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.List.route,
        ) {
            ListScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.Task.route,
            arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            val taskId = it.arguments?.getInt(TASK_ARGUMENT_KEY)
            TaskScreen(
                taskId = taskId,
                navHostController = navHostController
            )
        }
    }
}