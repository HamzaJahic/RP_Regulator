package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.Skills

class SkillDetailsViewModelFactory(private val skill: Skills): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SkillDetailsViewModel::class.java)) {
            return SkillDetailsViewModel(skill) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}