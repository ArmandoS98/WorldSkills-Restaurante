package com.aesc.restaurantews.ui.fragments.carta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello world"
    }

    val text: LiveData<String> = _text

}