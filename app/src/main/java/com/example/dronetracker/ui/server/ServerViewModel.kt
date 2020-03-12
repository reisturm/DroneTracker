package com.example.dronetracker.ui.server

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Login"
    }
    val text: LiveData<String> = _text
}