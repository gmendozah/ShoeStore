package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailViewModel(shoeList: Array<Shoe>) : ViewModel() {
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

    private val _errorResource = MutableLiveData<Int>()
    val errorResource: LiveData<Int>
        get() = _errorResource

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList


    init {
        _shoeList.value = shoeList.toMutableList()
    }

    fun saveShoe(): Int {
        val currentName = name.value
        val currentSize = size.value
        val currentCompany = company.value
        val currentDescription = description.value
        _errorResource.value = validateForm()
        if (_errorResource.value == 0) {
            _shoeList.value?.add(
                    Shoe(name = currentName.toString(), company = currentCompany.toString(), size = currentSize.toString().toDouble(), description = currentDescription.toString())
            )
        }
        return _errorResource.value ?: 0
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
}