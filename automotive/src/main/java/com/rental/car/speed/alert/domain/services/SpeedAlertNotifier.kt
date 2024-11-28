package com.rental.car.speed.alert.domain.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.rental.car.speed.alert.utils.Constants

/**
 * Notifies the user when the car exceeds the maximum speed limit
 *
 * @author [Ankit Pandey]
 */
object SpeedAlertNotifier {

    fun showNotification(context: Context, carId: String, currentSpeed: Float, maxSpeed: Int) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                Constants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle(Constants.NOTIFICATION_TITLE)
            .setContentText("Car $carId is at $currentSpeed km/h (limit: $maxSpeed km/h)")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(carId.hashCode(), notification)
    }
}