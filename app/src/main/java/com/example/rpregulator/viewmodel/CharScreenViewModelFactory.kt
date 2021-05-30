package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharScreenViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharScreenViewModel::class.java)) {
            return CharScreenViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}