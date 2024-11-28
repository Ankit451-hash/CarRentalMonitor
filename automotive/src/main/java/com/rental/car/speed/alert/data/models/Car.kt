package com.rental.car.speed.alert.data.models

import androidx.lifecycle.LiveData


/**
 * Represents a rented car with its unique identifier and current speed.
 *
 * @property carId The unique identifier of the rented car.
 * @property currentSpeed The current speed of the rented car.
 *
 * @author [Ankit Pandey]
 *
 */
data class Car(
    /**
     * The unique identifier of the rented car.
     */
    val carId: String,

    /**
     * The current speed for this rental.
     */
    val currentSpeed: LiveData<Float>
)
