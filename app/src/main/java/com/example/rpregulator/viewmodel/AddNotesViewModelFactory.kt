package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddNotesViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNotesViewModel::class.java)) {
            return AddNotesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}