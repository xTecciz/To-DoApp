package com.tck.myapplication.domain.use_cases.local

import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class SearchDatabaseUseCase(
    private val repository: Repository
) {
    operator fun invoke(searchQuery: String): Flow<List<ToDoTask>> {
        return repository.searchDatabase(searchQuery = searchQuery)
    }
}