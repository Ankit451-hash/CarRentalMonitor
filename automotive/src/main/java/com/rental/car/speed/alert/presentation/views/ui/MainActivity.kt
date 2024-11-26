package com.rental.car.speed.alert.presentation.views.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.rental.car.speed.alert.data.services.SpeedMonitorService
import java.util.jar.Manifest

/**
 * This Activity is used to launch SpeedMonitorService to monitor the speed of a vehicle.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request necessary permissions
        if (checkAndRequestPermissions()) {
            "USER1".startSpeedMonitoringService("CAR123")
        }
    }

    /**
     * Check and request required permissions for location and foreground service.
     */
    private fun checkAndRequestPermissions(): Boolean {
        val requiredPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE
        )
        val missingPermissions = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager
                .PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                missingPermissions.toTypedArray(), 1
            )
            return false
        }
        return true
    }

    /**
     * Start the SpeedMonitorService with carId and renterId.
     */
    private fun String.startSpeedMonitoringService(carId: String) {
        val intent = Intent(
            this@MainActivity,
            SpeedMonitorService::class.java
        ).apply {
            putExtra("CAR_ID", carId)
            putExtra("RENTER_ID", this@startSpeedMonitoringService)
        }
        ContextCompat.startForegroundService(this@MainActivity, intent)
    }

    /**
     * Handle the result of permission requests.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults.all
            { it == PackageManager.PERMISSION_GRANTED }
        ) {
            "USER1".startSpeedMonitoringService("CAR123")
        } else {
            Toast.makeText(this, "Permissions are required to monitor speed.",
                Toast.LENGTH_SHORT)
                .show()
        }
    }
}
