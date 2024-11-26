package com.rental.car.speed.alert.data.providers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.concurrent.fixedRateTimer

/**
 * Provides speed data for vehicles.
 *
 * This class manages a map of vehicle IDs to LiveData objects that emit the current speed of the vehicle.
 * It also simulates speed data for each vehicle using a timer.
 *
 */
class SpeedProvider {
    private val carSpeedData = mutableMapOf<String, MutableLiveData<Int>>()

    fun getSpeedLiveData(carId: String): LiveData<Int> {
        return carSpeedData.getOrPut(carId) {
            MutableLiveData<Int>().also { liveData ->
                simulateSpeedUpdates(carId, liveData)
            }
        }
    }

    private fun simulateSpeedUpdates(carId: String, liveData: MutableLiveData<Int>) {
        fixedRateTimer(
            name = "$carId-SpeedSimulation",
            initialDelay = 0,
            period = 2000
        ) {
            val randomSpeed = (0..120).random()
            liveData.postValue(randomSpeed)
        }
    }
}
