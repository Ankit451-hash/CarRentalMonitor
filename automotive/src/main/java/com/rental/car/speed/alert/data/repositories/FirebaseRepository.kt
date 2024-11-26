package com.rental.car.speed.alert.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Repository class for interacting with Firebase Realtime Database.
 * Provides methods for fetching data from the database.
 *
 */
class FirebaseRepository {
    private val database = FirebaseDatabase.getInstance().reference

    /**
     * Fetch the speed limit for a given car ID from Firebase.
     */
    fun getSpeedLimit(carId: String): LiveData<Int> {
        val speedLimitLiveData = MutableLiveData<Int>()
        database.child("Cars").child(carId).child("speed limit")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        speedLimitLiveData.postValue(snapshot.getValue(Int::class.java))
                    } else {
                        Log.e("FirebaseRepository", "No speed limit found for carId: $carId")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseRepository", "Error fetching speed limit: ${error.message}")
                }
            })
        return speedLimitLiveData
    }
}
