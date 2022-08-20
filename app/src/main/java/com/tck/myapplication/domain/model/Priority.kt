package com.tck.myapplication.domain.model

import androidx.compose.ui.graphics.Color
import com.tck.myapplication.ui.theme.*

sealed class Priority(val color: Color) {

    open fun name(): String {
        return "Priority"
    }

    open fun toColor():Color{
        return Purple200
    }

    object High : Priority(HighPriorityColor) {
        override fun name(): String {
            return "High"
        }

        override fun toColor(): Color {
            return HighPriorityColor
        }
    }

    object Medium : Priority(MediumPriorityColor) {
        override fun name(): String {
            return "Medium"
        }

        override fun toColor(): Color {
            return MediumPriorityColor
        }
    }

    object Low : Priority(LowPriorityColor) {
        override fun name(): String {
            return "Low"
        }

        override fun toColor(): Color {
            return LowPriorityColor
        }
    }

    object None : Priority(NonePriorityColor) {
        override fun name(): String {
            return "None"
        }
    }
}