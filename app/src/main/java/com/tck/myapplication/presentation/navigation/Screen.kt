package com.tck.myapplication.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object List : Screen("list_screen")
    object Task : Screen("task_screen/{taskId}") {
        fun passId(taskId: Int): String {
            return "task_screen/$taskId"
        }
    }
}