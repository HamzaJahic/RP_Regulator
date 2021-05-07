package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.User

class CharSelectViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharSelectViewModel::class.java)) {
            return CharSelectViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}