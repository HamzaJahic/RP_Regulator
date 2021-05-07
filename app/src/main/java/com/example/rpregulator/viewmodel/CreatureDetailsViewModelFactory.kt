package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.Creatures

class CreatureDetailsViewModelFactory(private val creature: Creatures): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreatureDetailsViewModel::class.java)) {
            return CreatureDetailsViewModel(creature) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}