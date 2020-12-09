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
        _shoeList.value = mutableListOf(
            Shoe(name = "Shoe 1", size = 9.5, company = "Company 1", description = "Description 1"),
            Shoe(name = "Shoe 2", size = 10.5, company = "Company 2", description = "Description 2"),
            Shoe(name = "Shoe 3", size = 8.5, company = "Company 3", description = "Description 3"),
        )
    }
}