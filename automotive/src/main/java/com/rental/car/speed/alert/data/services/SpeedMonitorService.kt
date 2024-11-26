package com.rental.car.speed.alert.data.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.rental.car.speed.alert.R.drawable.ic_notification
import com.rental.car.speed.alert.data.providers.SpeedProvider
import com.rental.car.speed.alert.data.repositories.FirebaseNotificationService
import com.rental.car.speed.alert.data.repositories.FirebaseRepository
import com.rental.car.speed.alert.presentation.viewmodels.SpeedMonitorViewModel

/**
 * A service that monitors the speed of a vehicle and sends notifications
 * when the speed exceeds a certain limit.
 *
 * This service is designed to run in the background and continuously monitor
 * the vehicle's speed. It uses a combination of GPS and accelerometer data
 * to determine the vehicle's speed and direction.
 */
class SpeedMonitorService : Service() {
    private lateinit var viewModel: SpeedMonitorViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = SpeedMonitorViewModel(SpeedProvider(this), FirebaseRepository(), FirebaseNotificationService())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createNotification())

        val carId = intent?.getStringExtra("CAR_ID") ?: return START_NOT_STICKY
        val renterId = intent.getStringExtra("RENTER_ID") ?: return START_NOT_STICKY

        viewModel.monitorSpeed(carId, renterId)

        return START_STICKY
    }

    private fun createNotification(): Notification {
        val channelId = "SpeedMonitoringChannel"
        val channel = NotificationChannel(
            channelId, "Speed Monitoring",
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Speed Monitoring")
            .setContentText("Monitoring vehicle speeds in real-time.")
            .setSmallIcon(ic_notification)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
