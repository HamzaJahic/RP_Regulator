package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChangePinViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangePinViewModel::class.java)) {
            return ChangePinViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}