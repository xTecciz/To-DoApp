package com.tck.myapplication.domain.use_cases.local

import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.repository.Repository

class InsertUpdateTaskUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(toDoTask: ToDoTask) {
        return repository.insertUpdateTask(toDoTask = toDoTask)
    }
}