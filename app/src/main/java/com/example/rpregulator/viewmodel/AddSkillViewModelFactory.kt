package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddSkillViewModelFactory(private val id: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddSkillViewModel::class.java)) {
            return AddSkillViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}