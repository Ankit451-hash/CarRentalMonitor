package com.rental.car.speed.alert.data.repositories

/**
 * A repository class that provides access to car-related data.
 *
 * Note: This implementation is a mock and may be replaced with a real implementation if required.
 *
 * @author [Ankit Pandey]
 */
class CarRepository {
    fun getCurrentSpeed(): Float {
        // Simulates fetching the current speed
        return (60..120).random().toFloat()
    }
}
