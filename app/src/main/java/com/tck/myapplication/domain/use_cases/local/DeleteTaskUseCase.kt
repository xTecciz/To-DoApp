package com.tck.myapplication.domain.use_cases.local

import com.tck.myapplication.domain.repository.Repository

class DeleteTaskUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(taskId: Int) {
        return repository.deleteTask(taskId = taskId)
    }
}