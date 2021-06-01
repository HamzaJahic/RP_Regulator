package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddBlessingViewModelFactory(private val id: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBlessingViewModel::class.java)) {
            return AddBlessingViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}