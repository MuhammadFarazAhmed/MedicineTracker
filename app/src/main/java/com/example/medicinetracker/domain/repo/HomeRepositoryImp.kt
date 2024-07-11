package com.example.medicinetracker.domain.repo


import android.content.Context
import androidx.room.withTransaction
import com.example.medicinetracker.components.isNetworkAvailable
import com.example.medicinetracker.db.ProblemDao
import com.example.medicinetracker.db.ProblemsDatabase
import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.model.Response
import com.example.medicinetracker.model.toProblemEntities
import com.example.medicinetracker.common.networkBoundResource
import com.example.medicinetracker.common.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow


class HomeRepositoryImp(
    private val context: Context,
    private val client: HttpClient,
    private val db: ProblemsDatabase,
    private val problemDao: ProblemDao
) : HomeRepository {


    override suspend fun fetchProblems(): Flow<ApiResult<List<ProblemEntity>>> =
        networkBoundResource(query = {
            problemDao.getAllProblems()
        }, fetch = {
            try {
                client.get("https://run.mocky.io/v3/f55f6e3c-c94a-49ef-92d4-64b1254c64c6")
                    .body<Response>().toProblemEntities()
            } catch (e: Exception) {
                emptyList()
            }
        }, saveFetchResult = { list ->
            if (list.isNotEmpty()) {
                try {
                    db.withTransaction {
                        problemDao.deleteAllProblems()
                        problemDao.insertProblem(list)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }, shouldFetch = {
            //Any business Logic will be here for renew cached data
            isNetworkAvailable(context)
        })

}
