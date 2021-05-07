package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddSkillViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddSkillViewModel::class.java)) {
            return AddSkillViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}