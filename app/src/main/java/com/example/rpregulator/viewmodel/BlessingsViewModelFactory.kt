package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BlessingsViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlessingsViewModel::class.java)) {
            return BlessingsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}