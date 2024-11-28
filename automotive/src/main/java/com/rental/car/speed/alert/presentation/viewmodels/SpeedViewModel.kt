package com.rental.car.speed.alert.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.car.speed.alert.data.repositories.RentalJsonRepository
import com.rental.car.speed.alert.domain.managers.CarPropertyManager

/**
 * ViewModel for monitoring speed and sending notifications when the speed limit is exceeded.
 * @param rentalJsonRepository to get rental data from json
 * @param carPropertyManager to get speed of vehicle from sensors
 * @author [Ankit Pandey]
 */
class SpeedViewModel(
    private val carPropertyManager: CarPropertyManager,
    private val rentalJsonRepository: RentalJsonRepository
) : ViewModel() {

    val currentSpeed: LiveData<Float> = carPropertyManager.currentSpeed

    fun getMaxSpeedForCar(carId: String): Int? {
        return rentalJsonRepository.getRentals().find { it.carId == carId }?.maxSpeed
    }

    override fun onCleared() {
        super.onCleared()
        carPropertyManager.unregisterSpeedListener()
    }
}
