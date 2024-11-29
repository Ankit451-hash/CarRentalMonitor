package com.rental.car.speed.alert.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rental.car.speed.alert.data.repositories.RentalJsonRepository
import com.rental.car.speed.alert.domain.managers.CarPropertyManager

/**
 * Factory class for creating SpeedViewModel with the necessary dependencies.
 */
class SpeedViewModelFactory(
    private val carPropertyManager: CarPropertyManager,
    private val rentalJsonRepository: RentalJsonRepository
) : ViewModelProvider.Factory {

    /**
     * Creates an instance of SpeedViewModel.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeedViewModel::class.java)) {
            // Return SpeedViewModel with dependencies injected
            return SpeedViewModel(carPropertyManager, rentalJsonRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}