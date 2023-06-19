package com.example.pj4test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartCamera : ViewModel() {
    private val _startCamera = MutableLiveData<Boolean>()
    val startCamera : LiveData<Boolean> = _startCamera

    fun startCamera(item: Boolean) {
        _startCamera.value = item
    }
}