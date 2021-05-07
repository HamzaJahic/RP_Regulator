package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel(context: Context) : ViewModel() {


    val _logout = MutableLiveData<Boolean?>()
    val logout: LiveData<Boolean?>
            get() = _logout

    val _navigateToChangePin = MutableLiveData<Boolean?>()
    val navigateToChangePin: LiveData<Boolean?>
        get() = _navigateToChangePin

    val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    fun navigateToChangePin(){
        _navigateToChangePin.value = true
        doneNavigateToChangePin()
    }

    fun doneNavigateToChangePin(){
        _navigateToChangePin.value = null
    }

    fun logOut(){
        _logout.value = true
        editor.clear()
        editor.apply()
        donelogOut()
    }

    fun donelogOut(){
        _logout.value = null
    }


}