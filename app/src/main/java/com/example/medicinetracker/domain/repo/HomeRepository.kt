package com.example.medicinetracker.domain.repo


import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.common.ApiResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    
    suspend fun fetchProblems() : Flow<ApiResult<List<ProblemEntity>>>
    
}