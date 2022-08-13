package com.tck.myapplication.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.tck.ToDoAppDB.Database
import com.tck.myapplication.data.toToDoTask
import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.repository.local.ToDoTaskDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class ToDoTaskDataSourceImpl(
    database: Database
) : ToDoTaskDataSource {

    private val queries = database.toDoTaskEntityQueries

    override fun getAllTasks(): Flow<List<ToDoTask>> {
        return queries.getAllTasks().asFlow().mapToList().map { list ->
            list.map {
                it.toToDoTask()
            }
        }
    }

    override fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return queries.getSelectedTask(taskId.toLong()).asFlow().mapToOne().map {
            it.toToDoTask()
        }
    }

    override suspend fun insertUpdateTask(toDoTask: ToDoTask) {
        withContext(Dispatchers.IO) {
            queries.insertTask(
                id = toDoTask.id.toLong(),
                title = toDoTask.title,
                description = toDoTask.description,
                priority = toDoTask.priority.toString()
            )
        }
    }

    override suspend fun deleteTask(taskId: Int) {
        withContext(Dispatchers.IO) {
            queries.deleteTaskById(taskId.toLong())
        }
    }

    override suspend fun deleteAllTasks() {
        withContext(Dispatchers.IO) {
            queries.deleteAllTasks()
        }
    }

    override fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
        return queries.searchDatabase(searchQuery, searchQuery).asFlow().mapToList().map { list ->
            list.map {
                it.toToDoTask()
            }
        }
    }

    override fun sortByLowPriority(): Flow<List<ToDoTask>> {
        return queries.sortByLowPriority().asFlow().mapToList().map { list ->
            list.map {
                it.toToDoTask()
            }
        }
    }

    override fun sortByHighPriority(): Flow<List<ToDoTask>> {
        return queries.sortByHighPriority().asFlow().mapToList().map { list ->
            list.map {
                it.toToDoTask()
            }
        }
    }
}