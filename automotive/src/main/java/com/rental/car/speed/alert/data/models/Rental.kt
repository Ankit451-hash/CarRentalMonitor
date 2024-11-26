package com.rental.car.speed.alert.data.models

/**
 * Represents a rental agreement between a renter and a car.
 *
 * @property carId the unique identifier of the rented car
 * @property renterId the unique identifier of the renter
 * @property maxSpeed the maximum allowed speed for this rental
 */
data class Rental(
    /**
     * The unique identifier of the rented car.
     */
    val carId: String,

    /**
     * The unique identifier of the renter.
     */
    val renterId: String,

    /**
     * The maximum allowed speed for this rental.
     */
    val maxSpeed: Int // Maximum permitted speed
)
