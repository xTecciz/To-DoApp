package com.tck.myapplication.domain.use_cases.local

import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.repository.Repository

class GetSelectedTaskUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(taskId: Int): ToDoTask? {
        return repository.getSelectedTask(taskId = taskId)
    }
}