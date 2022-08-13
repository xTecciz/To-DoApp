package com.tck.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tck.myapplication.util.Constants.ACTION_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {

        }
        composable(route = Screen.List.route, arguments = listOf(navArgument(ACTION_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = false
        })) {

        }
        composable(Screen.Task.route){

        }
    }
}