package com.tck.myapplication.domain.model

import androidx.compose.ui.graphics.Color
import com.tck.myapplication.ui.theme.HighPriorityColor
import com.tck.myapplication.ui.theme.LowPriorityColor
import com.tck.myapplication.ui.theme.MediumPriorityColor
import com.tck.myapplication.ui.theme.NonePriorityColor

sealed class Priority(val color: Color) {

    open fun name(): String {
        return "Priority"
    }

    object High : Priority(HighPriorityColor) {
        override fun name(): String {
            return "High"
        }
    }

    object Medium : Priority(MediumPriorityColor) {
        override fun name(): String {
            return "Medium"
        }
    }

    object Low : Priority(LowPriorityColor) {
        override fun name(): String {
            return "Low"
        }
    }

    object None : Priority(NonePriorityColor) {
        override fun name(): String {
            return "None"
        }
    }
}