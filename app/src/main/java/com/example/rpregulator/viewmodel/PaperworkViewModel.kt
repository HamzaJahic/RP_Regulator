package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.PaperworkFirebase
import com.example.rpregulator.models.PaperWork
import com.firebase.ui.database.FirebaseRecyclerOptions

class PaperworkViewModel(lifecycleOwner: LifecycleOwner) : ViewModel() {


    private val _navigateToAddPaperWork = MutableLiveData<Boolean?>()
    val navigateToAddPaperWork: LiveData<Boolean?>
        get() = _navigateToAddPaperWork


    private val _navigateToPaperWorkDetails = MutableLiveData<PaperWork?>()
    val navigateToPaperWorkDetails: LiveData<PaperWork?>
        get() = _navigateToPaperWorkDetails

    val options = FirebaseRecyclerOptions.Builder<PaperWork>()
        .setQuery(PaperworkFirebase.databaseReference, PaperWork::class.java)
        .setLifecycleOwner(lifecycleOwner)
        .build()


    fun navigateToAddPaperWork() {
        _navigateToAddPaperWork.value = true
        doneNavigateToAddPaperWork()

    }

    private fun doneNavigateToAddPaperWork() {
        _navigateToAddPaperWork.value = null
    }

    fun navigateToPaperWorkDetails(paperWork: PaperWork) {
        _navigateToPaperWorkDetails.value = paperWork
        doneNavigateToPaperWorkDetails()

    }

    private fun doneNavigateToPaperWorkDetails() {
        _navigateToPaperWorkDetails.value = null
    }

}