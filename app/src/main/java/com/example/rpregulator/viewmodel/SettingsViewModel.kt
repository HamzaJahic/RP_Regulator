package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel(context: Context) : ViewModel() {


    private val _logout = MutableLiveData<Boolean?>()
    val logout: LiveData<Boolean?>
        get() = _logout

    private val _navigateToChangePin = MutableLiveData<Boolean?>()
    val navigateToChangePin: LiveData<Boolean?>
        get() = _navigateToChangePin

    private val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun navigateToChangePin() {
        _navigateToChangePin.value = true
        doneNavigateToChangePin()
    }

    private fun doneNavigateToChangePin() {
        _navigateToChangePin.value = null
    }

    fun logOut() {
        _logout.value = true
        editor.clear()
        editor.apply()
        donelogOut()
    }

    private fun donelogOut() {
        _logout.value = null
    }


}