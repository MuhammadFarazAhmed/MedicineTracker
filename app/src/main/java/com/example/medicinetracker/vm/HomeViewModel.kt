package com.example.medicinetracker.vm

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinetracker.common.NetworkStatusTracker
import com.example.medicinetracker.domain.usecase.HomeUseCase
import com.example.medicinetracker.common.map
import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.common.ApiStatus.IDLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class HomeViewModel(
    application: Application,
    private val homeUseCase: HomeUseCase
) :
    AndroidViewModel(application) {

    var currentItem: ProblemEntity? = null
    var problems: MutableStateFlow<List<ProblemEntity>> = MutableStateFlow(emptyList())
    var status = mutableStateOf(IDLE)
    val isRefreshing = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    init {
        fetchProblems()
    }

    private fun fetchProblems() {
        viewModelScope.launch {
            homeUseCase.fetchProblems().collect {
                status.value = it.status
                it.data?.let { content -> problems.value = content }
                errorMessage.value = it.message.toString()
            }
        }
    }
}