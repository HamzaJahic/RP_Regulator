package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.BlessingsFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.firebase.ui.database.FirebaseRecyclerOptions

class BlessingsViewModel(val lifecycleOwner: LifecycleOwner) : ViewModel() {


    private val _navigateToAddBlessings = MutableLiveData<Boolean?>()
    val navigateToAddBlessings: LiveData<Boolean?>
        get() = _navigateToAddBlessings

    private val _navigateToBlessingDetail = MutableLiveData<CursesBlessingsHealth?>()
    val navigateToBlessingDetail: LiveData<CursesBlessingsHealth?>
        get() = _navigateToBlessingDetail

    val options = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
        .setQuery(BlessingsFirebase.databaseReference, CursesBlessingsHealth::class.java)
        .setLifecycleOwner(lifecycleOwner)
        .build()

    fun navigateToBlessingDetail(blessing: CursesBlessingsHealth) {
        _navigateToBlessingDetail.value = blessing
        doneNavigateToBlessingDetail()

    }

    private fun doneNavigateToBlessingDetail() {
        _navigateToBlessingDetail.value = null
    }


    fun navigateToAddBlessings() {
        _navigateToAddBlessings.value = true
        doneNavigateToAddBlessings()

    }

    private fun doneNavigateToAddBlessings() {
        _navigateToAddBlessings.value = null
    }


}