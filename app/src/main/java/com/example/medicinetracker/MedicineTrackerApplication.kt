package com.example.medicinetracker

import android.app.Application
import com.example.medicinetracker.di.AppModule
import com.example.medicinetracker.di.LocalModule
import com.example.medicinetracker.di.NetworkModule
import com.example.medicinetracker.di.featureModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MedicineTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MedicineTrackerApplication)
            modules(AppModule + NetworkModule + LocalModule + featureModules())
        }
    }
}
