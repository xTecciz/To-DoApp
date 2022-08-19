package com.tck.myapplication.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.tck.ToDoAppDB.Database
import com.tck.myapplication.data.local.ToDoTaskDataSourceImpl
import com.tck.myapplication.data.repository.RepositoryImpl
import com.tck.myapplication.domain.repository.local.ToDoTaskDataSource
import com.tck.myapplication.domain.use_cases.UseCases
import com.tck.myapplication.domain.use_cases.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSqliteDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = Database.Schema,
            context = app,
            name = "todoapp.db"
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(driver: SqlDriver): ToDoTaskDataSource {
        return ToDoTaskDataSourceImpl(Database(driver))
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RepositoryImpl): UseCases {
        return UseCases(
            getAllTasksUseCase = GetAllTasksUseCase(repository),
            getSelectedTaskUseCase = GetSelectedTaskUseCase(repository),
            insertUpdateTaskUseCase = InsertUpdateTaskUseCase(repository),
            deleteTaskUseCase = DeleteTaskUseCase(repository),
            deleteAllTasksUseCase = DeleteAllTasksUseCase(repository),
            searchDatabaseUseCase = SearchDatabaseUseCase(repository),
            sortByLowPriorityUseCase = SortByLowPriorityUseCase(repository),
            sortByHighPriorityUseCase = SortByHighPriorityUseCase(repository)
        )
    }
}