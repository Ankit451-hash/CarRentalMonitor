package com.rental.car.speed.alert.domain.managers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.car.speed.alert.data.models.Car
import com.rental.car.speed.alert.utils.Constants

/**
 * Manages car properties and provides access to their values.
 *
 * This class is designed to work with the Automotive SDK and will be tested with it.
 *
 * This class will provide real time speed data from the car.
 *
 * @author []Ankit Pandey]
 */

class CarPropertyManager(context: Context) {

    private var car: Car? = null
    private var carPropertyManager: CarPropertyManager? = null

    init {
        try {
            car = Car.createCar(context)
            carPropertyManager = car?.getCarManager(Car.PROPERTY_SERVICE) as? CarPropertyManager
        } catch (e: Exception) {
            // Log or handle cases where Car API is not available
            e.printStackTrace()
        }
    }

    private val _currentSpeed = MutableLiveData<Float>()
    val currentSpeed: LiveData<Float> get() = _currentSpeed

    /**
     * Registers a callback to monitor vehicle speed.
     */
    fun registerSpeedListener() {
        carPropertyManager.registerCallback(object : CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(event: CarPropertyManager.CarPropertyEvent) {
                if (event.propertyId == Constants.PERF_VEHICLE_SPEED) {
                    val speedMetersPerSecond =
                        event.value as? Float ?: Constants.SPEED_CONVERSION_FACTOR_ZERO
                    _currentSpeed.postValue(
                        speedMetersPerSecond *
                                Constants.SPEED_CONVERSION_FACTOR
                    ) // Convert m/s to km/h
                }
            }

            override fun onErrorEvent(propertyId: Int, zone: Int) {
                if (propertyId == Constants.PERF_VEHICLE_SPEED) {
                    _currentSpeed.postValue(Constants.SPEED_CONVERSION_FACTOR_ZERO) // Reset speed on error
                }
            }
        }, Constants.PERF_VEHICLE_SPEED, Constants.SENSOR_RATE_NORMAL)
    }

    /**
     * Unregisters the callback and releases the Car object.
     */
    fun unregisterSpeedListener() {
        carPropertyManager.unregisterCallback(Constants.PERF_VEHICLE_SPEED)
        car.disconnect()
    }
}