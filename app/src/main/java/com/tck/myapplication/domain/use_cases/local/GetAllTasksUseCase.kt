package com.tck.myapplication.domain.use_cases.local

import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<ToDoTask>> {
        return repository.getAllTasks()
    }
}