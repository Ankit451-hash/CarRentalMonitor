package com.rental.car.speed.alert.presentation.views.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.car.speed.alert.domain.services.SpeedMonitoringService
import com.rental.car.speed.alert.utils.Constants

/**
 * This Activity is used to launch SpeedMonitorService to monitor the speed of a vehicle.
 *
 * @author [Ankit Pandey]
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, SpeedMonitoringService::class.java)
        intent.putExtra(Constants.CAR_ID, Constants.CAR_123)
        startService(intent)
    }
}
