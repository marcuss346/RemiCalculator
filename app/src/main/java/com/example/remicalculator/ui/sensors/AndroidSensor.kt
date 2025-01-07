package com.example.remicalculator.ui.sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

abstract class AndroidSensor (
    private val context: Context,
    private val sensorFeature: String,
    private val sensorTypes: Int
): MeasurableSensor(sensorTypes), SensorEventListener {

    override val doesExsist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun startListening() {
        if(!doesExsist){
            return
        }
        if(!::sensorManager.isInitialized && sensor == null){
            sensorManager = context.getSystemService(SensorManager::class.java) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorTypes)
        }
        sensor.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun stopListening() {
        if(!doesExsist || !::sensorManager.isInitialized){
            return
        }
        sensor?.let {
            sensorManager.unregisterListener(this)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(!doesExsist){
            return
        }
        if(event?.sensor?.type == sensorTypes){
            onSensorValueChange?.invoke(event.values.toList())
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}