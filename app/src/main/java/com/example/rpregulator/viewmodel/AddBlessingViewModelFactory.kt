package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddBlessingViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBlessingViewModel::class.java)) {
            return AddBlessingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}