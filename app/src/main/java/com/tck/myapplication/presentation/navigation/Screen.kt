package com.tck.myapplication.presentation.navigation

import com.tck.myapplication.util.Constants.LIST_SCREEN
import com.tck.myapplication.util.Constants.SPLASH_SCREEN
import com.tck.myapplication.util.Constants.TASK_SCREEN

sealed class Screen(val route: String) {
    object Splash : Screen(SPLASH_SCREEN)
    object List : Screen(LIST_SCREEN) {
        fun passListAction(action:String):String{
            return "list_screen/$action"
        }
    }

    object Task : Screen(TASK_SCREEN) {
        fun passId(taskId: Int): String {
            return "task_screen/$taskId"
        }
    }
}