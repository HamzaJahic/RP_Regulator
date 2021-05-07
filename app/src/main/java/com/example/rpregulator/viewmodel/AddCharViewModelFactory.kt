package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddCharViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCharViewModel::class.java)) {
            return AddCharViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}