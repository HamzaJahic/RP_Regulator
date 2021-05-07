package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CursesViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursesViewModel::class.java)) {
            return CursesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}