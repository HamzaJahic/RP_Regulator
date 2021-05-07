package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddHealthViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddHealthViewModel::class.java)) {
            return AddHealthViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}