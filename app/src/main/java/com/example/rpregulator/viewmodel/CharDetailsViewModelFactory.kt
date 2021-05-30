package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.Chars

class CharDetailsViewModelFactory(private val char: Chars) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharDetailsViewModel::class.java)) {
            return CharDetailsViewModel(char) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}