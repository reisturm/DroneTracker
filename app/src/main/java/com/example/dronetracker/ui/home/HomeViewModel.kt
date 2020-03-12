package com.example.dronetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Drone Tracker"
    }
    val text: LiveData<String> = _text

    private val _desc = MutableLiveData<String>().apply {
        value = "Track drones in real-time in our flight tracker map"
    }
    val desc: LiveData<String> = _desc


}