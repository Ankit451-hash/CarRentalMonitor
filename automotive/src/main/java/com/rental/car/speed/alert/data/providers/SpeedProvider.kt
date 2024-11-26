package com.rental.car.speed.alert.data.providers

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Provides speed data for vehicles.
 *
 * This class manages a map of vehicle IDs to LiveData objects that emit the current speed of the vehicle.
 * It also simulates speed data for each vehicle using a timer.
 *
 */
class SpeedProvider(private val context: Context) {
    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val speedLiveData = MutableLiveData<Float>()

    fun getSpeedLiveData(): LiveData<Float> {
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1f
            ) { location ->
                val speed = location.speed * 3.6f // Convert m/s to km/h
                speedLiveData.postValue(speed)
            }

        return speedLiveData
    }
}
