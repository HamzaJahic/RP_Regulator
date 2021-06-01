package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.User

class PinViewModelFactory(private val user: User, private val context: Context) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PinViewModel::class.java)) {
            return PinViewModel(user, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}