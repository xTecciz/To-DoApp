package com.tck.myapplication.data.repository

import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.repository.Repository
import com.tck.myapplication.domain.repository.local.ToDoTaskDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val local: ToDoTaskDataSource
) : Repository {

    override fun getAllTasks(): Flow<List<ToDoTask>> {
        return local.getAllTasks()
    }

    override fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return local.getSelectedTask(taskId)
    }

    override suspend fun insertUpdateTask(toDoTask: ToDoTask) {
        local.insertUpdateTask(toDoTask)
    }

    override suspend fun deleteTask(taskId: Int) {
        local.deleteTask(taskId)
    }

    override suspend fun deleteAllTasks() {
        local.deleteAllTasks()
    }

    override fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
        return local.searchDatabase(searchQuery)
    }

    override fun sortByLowPriority(): Flow<List<ToDoTask>> {
        return local.sortByLowPriority()
    }

    override fun sortByHighPriority(): Flow<List<ToDoTask>> {
        return local.sortByHighPriority()
    }
}