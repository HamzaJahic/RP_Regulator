package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddInventoryViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddInventoryViewModel::class.java)) {
            return AddInventoryViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}