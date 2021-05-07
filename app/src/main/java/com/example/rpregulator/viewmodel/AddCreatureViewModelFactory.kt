package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddCreatureViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCreatureViewModel::class.java)) {
            return AddCreatureViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}