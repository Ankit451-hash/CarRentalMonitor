package com.rental.car.speed.alert.data.repositories

import com.rental.car.speed.alert.data.models.SpeedAlert

/**
- Interface for sending notifications when a speed alert is triggered.
 */
interface NotificationService {

    /**
    - Sends a notification when a speed alert is triggered.
    -
    - @param alert the SpeedAlert object containing the details of the speed alert
     */
    fun sendNotification(alert: SpeedAlert)
}
