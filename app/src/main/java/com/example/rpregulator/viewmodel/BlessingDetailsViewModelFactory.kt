package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.CursesBlessingsHealth

class BlessingDetailsViewModelFactory(private val blessing: CursesBlessingsHealth) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlessingDetailsViewModel::class.java)) {
            return BlessingDetailsViewModel(blessing) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}