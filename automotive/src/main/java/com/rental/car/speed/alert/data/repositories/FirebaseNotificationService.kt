package com.rental.car.speed.alert.data.repositories

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.rental.car.speed.alert.data.models.SpeedAlert

/**
 * Implementation of the NotificationInterface for sending notifications via Firebase.
 */
class FirebaseNotificationService : NotificationService {
    override fun sendNotification(alert: SpeedAlert) {
        val message = mapOf(
            "title" to "Speed Alert",
            "body" to "Car ID: ${alert.carId}, Speed: ${alert.currentSpeed} km/h, Limit: ${alert.maxSpeed} km/h"
        )

        FirebaseMessaging.getInstance().send(
            RemoteMessage.Builder("YOUR_PROJECT_ID@fcm.googleapis.com")
                .setMessageId(System.currentTimeMillis().toString())
                .putAllData(message)
                .build()
        ).addOnSuccessListener {
            Log.d("FirebaseNotification", "Notification sent for ${alert.carId}")
        }.addOnFailureListener {
            Log.e("FirebaseNotification", "Failed to send notification: ${it.message}")
        }
    }
}
