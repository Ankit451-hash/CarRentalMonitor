package com.rental.car.speed.alert.data.repositories

import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.rental.car.speed.alert.data.models.Rental

/**
 * A repository class that loads rental data from a JSON file.
 *
 * @author []Ankit Pandey]
 */
class RentalJsonRepository(private val context: Context) {

    fun getRentals(): List<Rental> {
        val rentalsJson = context.assets.open("rentals.json").bufferedReader()
            .use { it.readText() }
        val rentalListType = object : TypeToken<List<Rental>>() {}.type
        return Gson().fromJson(rentalsJson, rentalListType)
    }
}
