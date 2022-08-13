package com.tck.myapplication.domain.use_cases.local


import com.tck.myapplication.domain.repository.Repository

class DeleteAllTasksUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        return repository.deleteAllTasks()
    }
}