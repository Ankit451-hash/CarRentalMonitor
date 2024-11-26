package com.rental.car.speed.alert.presentation.views.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.car.speed.alert.data.services.SpeedMonitorService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SpeedMonitorService::class.java)
        startService(intent)
    }
}
