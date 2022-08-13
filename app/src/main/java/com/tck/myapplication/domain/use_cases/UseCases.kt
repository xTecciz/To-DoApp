package com.tck.myapplication.domain.use_cases

import com.tck.myapplication.domain.use_cases.local.*

data class UseCases(
    val getAllTasksUseCase: GetAllTasksUseCase,
    val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    val insertUpdateTaskUseCase: InsertUpdateTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    val searchDatabaseUseCase: SearchDatabaseUseCase,
    val sortByLowPriorityUseCase: SortByLowPriorityUseCase,
    val sortByHighPriorityUseCase: SortByHighPriorityUseCase
)