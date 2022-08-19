package com.tck.myapplication.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tck.myapplication.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun deleteTask(id: Int) {
        Log.d("TAGONE","deleteTaskViewmodel id $id")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteTaskUseCase(taskId = id)
        }
    }
}