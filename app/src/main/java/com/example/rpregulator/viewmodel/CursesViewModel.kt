package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.models.CursesBlessingsHealth

class CursesViewModel : ViewModel() {


    private val _navigateToAddCurses = MutableLiveData<Boolean?>()
    val navigateToAddCurses: LiveData<Boolean?>
        get() = _navigateToAddCurses

    private val _navigateToCurseDetails = MutableLiveData<CursesBlessingsHealth?>()
    val navigateToCurseDetails: LiveData<CursesBlessingsHealth?>
        get() = _navigateToCurseDetails


    fun navigateToCurseDetails(curse: CursesBlessingsHealth) {
        _navigateToCurseDetails.value = curse
        doneNavigateToCurseDetails()

    }

    private fun doneNavigateToCurseDetails() {
        _navigateToCurseDetails.value = null
    }


    fun navigateToAddCurses() {
        _navigateToAddCurses.value = true
        doneNavigateToAddCurses()

    }

    private fun doneNavigateToAddCurses() {
        _navigateToAddCurses.value = null
    }


}