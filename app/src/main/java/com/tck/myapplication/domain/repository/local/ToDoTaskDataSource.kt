package com.tck.myapplication.domain.repository.local

import com.tck.myapplication.domain.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoTaskDataSource {

    fun getAllTasks(): Flow<List<ToDoTask>>
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>
    suspend fun insertTask(toDoTask: ToDoTask)
    suspend fun updateTask(toDoTask: ToDoTask)
    suspend fun deleteTask(taskId: Int)
    suspend fun deleteAllTasks()
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>
    fun sortByLowPriority(): Flow<List<ToDoTask>>
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}