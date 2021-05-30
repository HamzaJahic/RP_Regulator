package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatsViewModel : ViewModel() {


    private val _navigateToAddStats = MutableLiveData<Boolean?>()
    val navigateToAddStats: LiveData<Boolean?>
        get() = _navigateToAddStats


    fun navigateToAddStats() {
        _navigateToAddStats.value = true
        doneNavigateToAddStats()

    }

    private fun doneNavigateToAddStats() {
        _navigateToAddStats.value = null
    }


}