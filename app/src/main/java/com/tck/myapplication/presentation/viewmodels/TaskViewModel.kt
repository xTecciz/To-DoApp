package com.tck.myapplication.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tck.myapplication.domain.model.Priority
import com.tck.myapplication.domain.model.ToDoTask
import com.tck.myapplication.domain.use_cases.UseCases
import com.tck.myapplication.util.Constants
import com.tck.myapplication.util.Constants.TASK_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.Low)

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            useCases.getSelectedTaskUseCase(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = null,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            useCases.insertUpdateTaskUseCase(toDoTask = toDoTask)
        }
    }

    fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            useCases.insertUpdateTaskUseCase(toDoTask = toDoTask)
        }
    }

    fun validateFields(): Boolean {
        return title.value.isEmpty() && description.value.isEmpty()
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < Constants.MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id!!
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 1
            title.value = ""
            description.value = ""
            priority.value = Priority.Low
        }
    }
}