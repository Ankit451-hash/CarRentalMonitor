package com.rental.car.speed.alert.domain.services

import android.annotation.SuppressLint
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import com.google.firebase.database.FirebaseDatabase
import com.rental.car.speed.alert.data.models.Car
import com.rental.car.speed.alert.data.models.SpeedAlert
import com.rental.car.speed.alert.data.repositories.RentalJsonRepository
import com.rental.car.speed.alert.domain.managers.CarPropertyManager
import com.rental.car.speed.alert.presentation.viewmodels.SpeedViewModel
import com.rental.car.speed.alert.utils.Constants

/**
 * A service that monitors the speed of a vehicle and sends notifications
 * when the speed exceeds a certain limit.
 *
 * This service is designed to run in the background and continuously monitor
 * the vehicle's speed. It uses a combination of GPS and accelerometer data
 * to determine the vehicle's speed and direction.
 *
 * @author []Ankit Pandey]
 */
class SpeedMonitoringService : LifecycleService() {

    private lateinit var speedViewModel: SpeedViewModel

    override fun onCreate() {
        super.onCreate()
        val carPropertyManager = CarPropertyManager(Car.createCar(this))
        val rentalRepository = RentalJsonRepository(this)
        speedViewModel = SpeedViewModel(carPropertyManager, rentalRepository)

        carPropertyManager.registerSpeedListener()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val carId = intent?.getStringExtra(Constants.CAR_ID) ?: return START_NOT_STICKY

        speedViewModel.currentSpeed.observe(this, Observer { speed ->
            val maxSpeed = speedViewModel.getMaxSpeedForCar(carId)
            if (maxSpeed != null && speed > maxSpeed) {
                sendAlertToFirebase(carId, speed, maxSpeed)
                showUserNotification(carId, speed, maxSpeed)
            }
        })

        return START_STICKY
    }

    private fun sendAlertToFirebase(carId: String, currentSpeed: Float, maxSpeed: Int) {
        val database = FirebaseDatabase.getInstance()
        val alertRef = database.getReference(Constants.TEXT_SPEED_ALERTS)
        val alert = SpeedAlert(carId, currentSpeed, maxSpeed)
        alertRef.push().setValue(alert)
    }

    private fun showUserNotification(carId: String, currentSpeed: Float, maxSpeed: Int) {
        SpeedAlertNotifier.showNotification(this, carId, currentSpeed, maxSpeed)
    }

    @SuppressLint("MissingSuperCall")
    fun onBind(intent: Intent?): IBinder? = null
}