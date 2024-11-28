package com.rental.car.speed.alert.data.models

/**
 * Represents a rental agreement between a renter and a car.
 *
 * @property carId the unique identifier of the rented car
 * @property renterId the unique identifier of the renter
 * @property carName the unique identifier of the car being rented
 * @property maxSpeed the maximum allowed speed for this rental
 *
 * @author [Ankit Pandey]
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
    val maxSpeed: Int ,

    /**
     * The unique name of the car being rented.
     */
    val carName: String
)
