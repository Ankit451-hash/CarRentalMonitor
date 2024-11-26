package com.rental.car.speed.alert.data.repositories

import com.rental.car.speed.alert.data.models.SpeedAlert

/**
 * Implementation of the NotificationInterface for sending notifications via Firebase.
 */
class FirebaseNotificationService : NotificationService {

    override fun sendNotification(alert: SpeedAlert) {
        val message = mapOf(
            "title" to "Speed Alert",
            "body" to "Car ID: ${alert.carId}, Speed: ${alert.currentSpeed} km/h, " +
                    "Limit: ${alert.maxSpeed} km/h"
        )

        // Simulating Firebase REST API call (replace with actual HTTP client implementation)
        println("Sending Firebase notification with message: $message")

        // Example placeholder for REST API logic
        /*
        val fcmUrl = "https://fcm.googleapis.com/fcm/send"
        val payload = mapOf(
            "to" to "/topics/${alert.carId}",
            "notification" to message
        )
        val headers = mapOf(
            "Authorization" to "key=YOUR_SERVER_KEY",
            "Content-Type" to "application/json"
        )
        */
    }
}
