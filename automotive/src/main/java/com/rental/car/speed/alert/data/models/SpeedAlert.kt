package com.rental.car.speed.alert.data.models

/**
 * Represents a speed alert triggered when a renter exceeds the maximum allowed speed.
 *
 * @property carId the unique identifier of the car that triggered the alert
 * @property renterId the unique identifier of the renter who triggered the alert
 * @property currentSpeed the current speed of the car that triggered the alert
 * @property maxSpeed the maximum allowed speed for the rental
 */

data class SpeedAlert(
    /**
     * The unique identifier of the car that triggered the alert.
     */
    val carId: String,

    /**
     * The unique identifier of the renter who triggered the alert.
     */
    val renterId: String,

    /**
     * The current speed of the car that triggered the alert.
     */
    val currentSpeed: Int,

    /**
     * The maximum allowed speed for the rental.
     */
    val maxSpeed: Int
)
