package com.example.medicinetracker.common

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(crossinline query: () -> Flow<ResultType>,
                                                          crossinline fetch: suspend () -> RequestType,
                                                          crossinline saveFetchResult: suspend (RequestType) -> Unit,
                                                          crossinline shouldFetch: suspend (ResultType) -> Boolean = { true }) =
        flow {
            emit(ApiResult.Loading(true))
            val data = query().first()

            val flow = if (shouldFetch(data)) {
                emit(ApiResult.Loading(true))

                try {
                    saveFetchResult(fetch())
                    query().map { ApiResult.Success(it) }
                } catch (throwable: Throwable) {
                    query().map { ApiResult.Error(throwable.message ?: "Something went wrong") }
                }
            } else {
                query().map { ApiResult.Success(it) }
            }

            emitAll(flow)
        }
