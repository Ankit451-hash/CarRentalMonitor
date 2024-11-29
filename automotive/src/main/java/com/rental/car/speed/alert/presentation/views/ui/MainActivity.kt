package com.rental.car.speed.alert.presentation.views.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.rental.car.speed.alert.data.repositories.RentalJsonRepository
import com.rental.car.speed.alert.domain.managers.CarPropertyManager
import com.rental.car.speed.alert.domain.services.SpeedMonitoringService
import com.rental.car.speed.alert.presentation.viewmodels.SpeedViewModel
import com.rental.car.speed.alert.presentation.viewmodels.SpeedViewModelFactory

/**
 * This Activity is used to launch SpeedMonitorService to monitor the speed of a vehicle.
 *
 * @author [Ankit Pandey]
 */
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: SpeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carPropertyManager = CarPropertyManager(context = baseContext)
        val rentalRepository = RentalJsonRepository(this)

        viewModel = ViewModelProvider(
            this,
            SpeedViewModelFactory(
                carPropertyManager,
                rentalRepository
            )
        ).get(SpeedViewModel::class.java)

        viewModel.currentSpeed.observe(this, { speed ->
            // Handle the current speed (e.g., update UI)
            println("Current speed: $speed km/h")
        })

        // Example: Get the maximum speed for a car with a specific car ID
        val carId = "CAR123" // Example car ID
        val maxSpeed = viewModel.getMaxSpeedForCar(carId)
        println("Max speed for car $carId: $maxSpeed km/h")


        val serviceIntent = Intent(this, SpeedMonitoringService::class.java)
        startService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        val serviceIntent = Intent(this, SpeedMonitoringService::class.java)
        stopService(serviceIntent)
    }
}
