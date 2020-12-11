package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModelFactory(private val shoeList: Array<Shoe>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeDetailViewModel::class.java)) {
            return ShoeDetailViewModel(shoeList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}