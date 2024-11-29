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

    // LiveData representing the current speed of the vehicle
    val currentSpeed: LiveData<Float> = carPropertyManager.currentSpeed

    /**
     * Fetches the maximum allowed speed for a specific car from the rental JSON data.
     *
     * @param carId the car ID to find the speed limit for.
     * @return the max speed for the car or null if not found.
     */
    fun getMaxSpeedForCar(carId: String): Int? {
        return rentalJsonRepository.getRentals().find { it.carId == carId }?.maxSpeed
    }

    /**
     * Cleans up resources when the ViewModel is destroyed.
     */
    override fun onCleared() {
        super.onCleared()
        // Unregister the listener to stop receiving updates for speed
        carPropertyManager.unregisterSpeedListener()
    }
}
