package com.example.remicalculator.ui.sensors

abstract class MeasurableSensor(
    protected val sensorType: Int
) {

    protected var onSensorValueChange: ((List<Float>) -> Unit)? = null

    abstract val doesExsist: Boolean


    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (List<Float>) -> Unit) {
        onSensorValueChange = listener
    }

}