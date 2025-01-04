package com.example.remicalculator.di

import android.app.Application
import androidx.room.Room
import com.example.remicalculator.Data.GameRepository
import com.example.remicalculator.Data.database.AppDatabase
import com.example.remicalculator.Data.dao.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "remi_calculator_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(database: AppDatabase): GameDao {
        return database.gameDao()
    }

    @Provides
    @Singleton
    fun provideGameRepository(gameDao: GameDao): GameRepository {
        return GameRepository(gameDao)
    }
}