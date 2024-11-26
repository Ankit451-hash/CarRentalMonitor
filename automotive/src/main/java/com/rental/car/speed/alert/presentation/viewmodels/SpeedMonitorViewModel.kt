package com.rental.car.speed.alert.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rental.car.speed.alert.data.models.SpeedAlert
import com.rental.car.speed.alert.data.providers.SpeedProvider
import com.rental.car.speed.alert.data.repositories.FirebaseRepository
import com.rental.car.speed.alert.data.repositories.NotificationService

/**
 * ViewModel for monitoring speed and sending notifications when the speed limit is exceeded.
 *
 * @param notificationService the NotificationInterface instance for sending notifications
 */
class SpeedMonitorViewModel(
    private val speedProvider: SpeedProvider,
    private val firebaseRepository: FirebaseRepository,
    private val notificationService: NotificationService
) : ViewModel() {

    private val _alerts = MutableLiveData<SpeedAlert>()
    val alerts: LiveData<SpeedAlert> = _alerts

    /**
     * Monitor speed for a specific car and renter.
     */
    fun monitorSpeed(carId: String, renterId: String) {
        val speedLimitLiveData = firebaseRepository.getSpeedLimit(carId)

        speedProvider.getSpeedLiveData().observeForever { currentSpeed ->
            speedLimitLiveData.observeForever { maxSpeed ->
                if (currentSpeed > maxSpeed) {
                    val alert = SpeedAlert(carId, currentSpeed.toString(), maxSpeed, renterId)
                    _alerts.postValue(alert)
                    notificationService.sendNotification(alert)
                }
            }
        }
    }
}
