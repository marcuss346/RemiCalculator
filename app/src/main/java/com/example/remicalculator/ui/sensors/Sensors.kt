package com.example.remicalculator.ui.sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor


class ProximitySensor(
    context: Context
): AndroidSensor(context,
    PackageManager.FEATURE_SENSOR_PROXIMITY,
    android.hardware.Sensor.TYPE_PROXIMITY){

}