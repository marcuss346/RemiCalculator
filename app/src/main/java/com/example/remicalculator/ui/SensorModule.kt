package com.example.remicalculator.ui

import android.app.Application
import com.example.remicalculator.ui.sensors.MeasurableSensor
import com.example.remicalculator.ui.sensors.ProximitySensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    fun provideProxSensor(app: Application): MeasurableSensor{
        return ProximitySensor(app)
    }
}