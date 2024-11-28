package com.rental.car.speed.alert.data.models

/**
 * Represents a speed alert triggered when a renter exceeds the maximum allowed speed.
 *
 * @property carId the unique identifier of the car that triggered the alert
 * @property currentSpeed the current speed of the car that triggered the alert
 * @property maxSpeed the maximum allowed speed for the rental
 *
 * @author [Ankit Pandey]
 */

data class SpeedAlert(
    /**
     * The unique identifier of the car that triggered the alert.
     */
    val carId: String,

    /**
     * The current speed of the car that triggered the alert.
     */
    val currentSpeed: Float,

    /**
     * The maximum allowed speed for the rental.
     */
    val maxSpeed: Int
)
