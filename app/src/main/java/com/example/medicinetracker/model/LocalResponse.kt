package com.example.medicinetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "problem")
data class ProblemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val problemType: String = "",
    val medications: List<MedicationEntity> = arrayListOf(),
    val labs: List<LabEntity> = arrayListOf()
)

// Entity for storing medications
@Entity(tableName = "medication")
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val problemId: Long = -1, // Foreign key to ProblemEntity
    val name: String,
    val dose: String?,
    val strength: String
)

// Entity for storing labs
@Entity(tableName = "lab")
data class LabEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val problemId: Long, // Foreign key to ProblemEntity
    val missingField: String
)