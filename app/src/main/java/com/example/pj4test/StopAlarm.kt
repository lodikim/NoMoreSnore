package com.example.pj4test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StopAlarm : ViewModel() {
    private val _stopAlarm = MutableLiveData<Boolean>()
    val stopAlarm : LiveData<Boolean> = _stopAlarm

    fun stopAlarm(item: Boolean) {
        _stopAlarm.value = item
    }
}