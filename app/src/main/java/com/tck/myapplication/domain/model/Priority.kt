package com.tck.myapplication.domain.model

import androidx.compose.ui.graphics.Color
import com.tck.myapplication.ui.theme.HighPriorityColor
import com.tck.myapplication.ui.theme.LowPriorityColor
import com.tck.myapplication.ui.theme.MediumPriorityColor
import com.tck.myapplication.ui.theme.NonePriorityColor

sealed class Priority(val color: Color) {
    object High : Priority(HighPriorityColor)
    object Medium : Priority(MediumPriorityColor)
    object Low : Priority(LowPriorityColor)
    object None : Priority(NonePriorityColor)
}