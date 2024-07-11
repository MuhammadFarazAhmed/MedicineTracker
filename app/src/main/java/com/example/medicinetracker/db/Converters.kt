package com.example.medicinetracker.db

import androidx.room.TypeConverter
import com.example.medicinetracker.model.LabEntity
import com.example.medicinetracker.model.MedicationEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    @JvmStatic
    fun medicationsToString(medications: List<MedicationEntity>?): String {
        return Gson().toJson(medications)
    }

    @TypeConverter
    @JvmStatic
    fun stringToMedications(value: String): List<MedicationEntity> {
        val listType = object : TypeToken<List<MedicationEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun labsToString(labs: List<LabEntity>?): String {
        return Gson().toJson(labs)
    }

    @TypeConverter
    @JvmStatic
    fun stringToLabs(value: String): List<LabEntity> {
        val listType = object : TypeToken<List<LabEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}