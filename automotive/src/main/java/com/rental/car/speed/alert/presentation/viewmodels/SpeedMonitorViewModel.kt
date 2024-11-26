package com.rental.car.speed.alert.presentation.viewmodels

import androidx.lifecycle.Observer
import com.rental.car.speed.alert.data.models.Rental
import com.rental.car.speed.alert.data.models.SpeedAlert
import com.rental.car.speed.alert.data.providers.SpeedProvider
import com.rental.car.speed.alert.data.repositories.NotificationService

/**
 * ViewModel for monitoring speed and sending notifications when the speed limit is exceeded.
 *
 * @param notificationService the NotificationInterface instance for sending notifications
 */
class SpeedMonitorViewModel(
    private val notificationService: NotificationService,
    private val speedProvider: SpeedProvider
) {
    private val activeObservers = mutableMapOf<String, Observer<Int>>()

    fun startMonitoring(rentals: List<Rental>) {
        rentals.forEach { rental ->
            val speedLiveData = speedProvider.getSpeedLiveData(rental.carId)
            val observer = Observer<Int> { currentSpeed ->
                if (currentSpeed > rental.maxSpeed) {
                    val alert = SpeedAlert(
                        carId = rental.carId,
                        renterId = rental.renterId,
                        currentSpeed = currentSpeed,
                        maxSpeed = rental.maxSpeed
                    )
                    notificationService.sendNotification(alert)
                }
            }
            speedLiveData.observeForever(observer)
            activeObservers[rental.carId] = observer
        }
    }

    fun stopMonitoring(rentals: List<Rental>) {
        rentals.forEach { rental ->
            activeObservers.remove(rental.carId)?.let {
                speedProvider.getSpeedLiveData(rental.carId).removeObserver(it)
            }
        }
    }
}
