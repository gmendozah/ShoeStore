package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListingViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<List<Shoe>> = Transformations.map(_shoeList) {
        it.toList()
    }

    init {
        _shoeList.value = mutableListOf()
    }
}