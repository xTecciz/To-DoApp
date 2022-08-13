package com.tck.myapplication.presentation.navigation

import com.tck.myapplication.util.Constants.LIST_SCREEN
import com.tck.myapplication.util.Constants.SPLASH_SCREEN
import com.tck.myapplication.util.Constants.TASK_SCREEN

sealed class Screen(val route: String) {
    object Splash : Screen(SPLASH_SCREEN)
    object List : Screen(LIST_SCREEN)
    object Task : Screen(TASK_SCREEN)
}