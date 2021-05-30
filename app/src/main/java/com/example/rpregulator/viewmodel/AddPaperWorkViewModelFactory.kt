package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddPaperWorkViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPaperWorkViewModel::class.java)) {
            return AddPaperWorkViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}