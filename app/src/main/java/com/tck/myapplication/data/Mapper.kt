package com.tck.myapplication.data

import com.tck.myapplication.domain.model.Priority
import com.tck.myapplication.domain.model.ToDoTask
import todoappdb.ToDoTaskEntity


fun ToDoTaskEntity.toToDoTask() = ToDoTask(
    id = id.toInt(),
    title = title,
    description = description,
    priority = priority.toPriority()
)

fun String.toPriority(): Priority {
    return when {
        this == Priority.Low.name() -> Priority.Low
        this == Priority.Medium.name() -> Priority.Medium
        this == Priority.High.name() -> Priority.High
        else -> Priority.None
    }
}
