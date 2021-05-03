package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {
    private var _onBoarding = MutableLiveData<Boolean>(false)
    val onBoarding: LiveData<Boolean> get() = _onBoarding

    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>> get() = _shoeList

    fun addShoe(name: String, company: String, size: Double, description: String) {
        val shoe = Shoe(name, size, company, description, listOf())
        if (_shoeList.value.isNullOrEmpty()) {
            _shoeList.value = arrayListOf(shoe)
        } else {
            var currentList = _shoeList.value
            currentList?.add(shoe)
            _shoeList.value = currentList
        }
    }

    fun avoidOnBoarding() {
        _onBoarding.value = true
    }

    fun allowOnBoarding() {
        _onBoarding.value = false
    }
}