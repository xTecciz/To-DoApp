package com.tck.myapplication.domain.repository.local

import com.tck.myapplication.domain.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoTaskDataSource {

    fun getAllTasks(): Flow<List<ToDoTask>>
    suspend fun getSelectedTask(taskId: Int): ToDoTask?
    suspend fun insertUpdateTask(toDoTask: ToDoTask)
    suspend fun deleteTask(taskId: Int)
    suspend fun deleteAllTasks()
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>
    fun sortByLowPriority(): Flow<List<ToDoTask>>
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}