package com.example.medicinetracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.medicinetracker.model.LabEntity
import com.example.medicinetracker.model.MedicationEntity
import com.example.medicinetracker.model.ProblemEntity

@Database(entities = [ProblemEntity::class, MedicationEntity::class, LabEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProblemsDatabase : RoomDatabase() {
    abstract fun problemDao(): ProblemDao
}
