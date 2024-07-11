package com.example.medicinetracker.domain.usecase

import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.common.ApiResult
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchProblems(): Flow<ApiResult<List<ProblemEntity>>>
}