package com.example.rpregulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.models.CursesBlessingsHealth

class CurseDetailsViewModelFactory(private val curse: CursesBlessingsHealth) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurseDetailsViewModel::class.java)) {
            return CurseDetailsViewModel(curse) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}