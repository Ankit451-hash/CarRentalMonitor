package com.rental.car.speed.alert.data.services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.rental.car.speed.alert.data.models.Rental
import com.rental.car.speed.alert.data.providers.SpeedProvider
import com.rental.car.speed.alert.data.repositories.FirebaseNotificationService
import com.rental.car.speed.alert.presentation.viewmodels.SpeedMonitorViewModel
import com.rental.car.speed.alert.utils.Constants

/**
 * A service that monitors the speed of a vehicle and sends notifications
 * when the speed exceeds a certain limit.
 *
 * This service is designed to run in the background and continuously monitor
 * the vehicle's speed. It uses a combination of GPS and accelerometer data
 * to determine the vehicle's speed and direction.
 */
class SpeedMonitorService : Service() {

    private val speedProvider = SpeedProvider()
    private val notificationService = FirebaseNotificationService()
    private val viewModel = SpeedMonitorViewModel(notificationService, speedProvider)

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
    }

    @SuppressLint("ForegroundServiceType")
    private fun startForegroundService() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel(
            Constants.NOTIFICATION_CHANNEL_ID,
            Constants.NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)

        val notification = Notification.Builder(this, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Speed Monitoring")
            .setContentText("Monitoring vehicle speeds in real-time.")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .build()

        startForeground(Constants.FOREGROUND_NOTIFICATION_ID, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val rentals = listOf(
            Rental("CAR123", "USER1", 80),
            Rental("CAR456", "USER2", 100)
        )
        viewModel.startMonitoring(rentals)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(STOP_FOREGROUND_REMOVE)
        val rentals = listOf(
            Rental("CAR123", "USER1", 80),
            Rental("CAR456", "USER2", 100)
        )
        viewModel.stopMonitoring(rentals)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
