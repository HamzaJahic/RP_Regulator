package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.Inventory

class InventoryDetailsViewModelFactory(private val inventory: Inventory) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryDetailsViewModel::class.java)) {
            return InventoryDetailsViewModel(inventory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}