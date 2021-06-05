package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HealthViewModel : ViewModel() {

    private val _navigateToAddHealth = MutableLiveData<Boolean?>()
    val navigateToAddHealth: LiveData<Boolean?>
        get() = _navigateToAddHealth

    fun navigateToAddHealth() {
        _navigateToAddHealth.value = true
        doneNavigateToAddHealth()
    }

    private fun doneNavigateToAddHealth() {
        _navigateToAddHealth.value = null
    }


}