package com.example.medicinetracker.db

import androidx.room.*
import com.example.medicinetracker.model.ProblemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProblemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProblem(problems: List<ProblemEntity>)

    @Query("SELECT * FROM problem WHERE id = :problemId")
    suspend fun getProblemById(problemId: Long): ProblemEntity?

    @Query("DELETE FROM problem")
    suspend fun deleteAllProblems()

    @Query("SELECT * FROM problem ORDER BY id ASC")
     fun getAllProblems() : Flow<List<ProblemEntity>>

}