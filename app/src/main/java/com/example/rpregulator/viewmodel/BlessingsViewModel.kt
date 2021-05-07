package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.models.CursesBlessingsHealth

class BlessingsViewModel() : ViewModel() {


    private val _navigateToAddBlessings = MutableLiveData<Boolean?>()
    val navigateToAddBlessings: LiveData<Boolean?>
    get() = _navigateToAddBlessings

    private val _navigateToBlessingDetail = MutableLiveData<CursesBlessingsHealth?>()
    val navigateToBlessingDetail: LiveData<CursesBlessingsHealth?>
        get() = _navigateToBlessingDetail


    fun navigateToBlessingDetail(blessing: CursesBlessingsHealth){
        _navigateToBlessingDetail.value = blessing
        doneNavigateToBlessingDetail()

    }

    fun doneNavigateToBlessingDetail(){
        _navigateToBlessingDetail.value = null
    }


    fun navigateToAddBlessings(){
        _navigateToAddBlessings.value = true
        doneNavigateToAddBlessings()
      
    }

    fun doneNavigateToAddBlessings(){
        _navigateToAddBlessings.value = null
    }


}