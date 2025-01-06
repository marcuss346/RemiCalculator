package com.example.remicalculator

import android.app.Application
import androidx.room.Room
import com.example.remicalculator.Data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize the database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "rummy_calculator_database"
        ).build()
    }
}
