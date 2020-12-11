package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailViewModel : ViewModel() {
    // Two-way databinding, exposing MutableLiveData
    val name = MutableLiveData<String>()

    // Two-way databinding, exposing MutableLiveData
    val size = MutableLiveData<String>()

    // Two-way databinding, exposing MutableLiveData
    val company = MutableLiveData<String>()

    // Two-way databinding, exposing MutableLiveData
    val description = MutableLiveData<String>()

    private val _eventAddShoe = MutableLiveData<Boolean>()
    val eventAddShoe: LiveData<Boolean>
        get() = _eventAddShoe

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int>
        get() = _snackbarText

    fun saveShoe(): Shoe? {
        val currentName = name.value
        val currentSize = size.value
        val currentCompany = company.value
        val currentDescription = description.value
        _snackbarText.value = validateForm()
        return if (_snackbarText.value == 0) {
            Shoe(name = currentName.toString(), company = currentCompany.toString(), size = currentSize.toString().toDouble(), description = currentDescription.toString())
        } else {
            null
        }
    }

    private fun validateForm(): Int {
        return when {
            name.value == null || name.value?.isEmpty() == true -> {
                return R.string.nameValidation
            }
            size.value == null || size.value?.isEmpty() == true -> {
                return R.string.sizeValidation
            }
            company.value == null || company.value?.isEmpty() == true -> {
                return R.string.companyValidation
            }
            description.value == null || description.value?.isEmpty() == true -> {
                return R.string.descriptionValidation
            }
            size.value != null && size.value.toString().toDouble() <= 0 -> {
                return R.string.sizeValidationValue
            }
            else -> 0
        }
    }

    fun onAddShoe() {
        _eventAddShoe.value = true
    }

    fun onAddShoeComplete() {
        _eventAddShoe.value = false
    }
}