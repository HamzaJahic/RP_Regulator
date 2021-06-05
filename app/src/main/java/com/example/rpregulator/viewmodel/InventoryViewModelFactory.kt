package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InventoryViewModelFactory(val lifecycleOwner: LifecycleOwner) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            return InventoryViewModel(lifecycleOwner) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}