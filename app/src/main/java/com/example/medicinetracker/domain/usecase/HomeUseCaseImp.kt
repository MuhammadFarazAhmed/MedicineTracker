package com.example.medicinetracker.domain.usecase

import com.example.medicinetracker.domain.repo.HomeRepository
import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.common.ApiResult
import kotlinx.coroutines.flow.Flow


class HomeUseCaseImp(private val homeRepository: HomeRepository) : HomeUseCase {

    override suspend fun fetchProblems(): Flow<ApiResult<List<ProblemEntity>>> = homeRepository.fetchProblems()
}

