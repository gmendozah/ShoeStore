package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe

class ShoeListingViewModelFactory(private val shoeList: Array<Shoe>?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeListingViewModel::class.java)) {
            return ShoeListingViewModel(shoeList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}