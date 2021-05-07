package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddCurseViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCurseViewModel::class.java)) {
            return AddCurseViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}