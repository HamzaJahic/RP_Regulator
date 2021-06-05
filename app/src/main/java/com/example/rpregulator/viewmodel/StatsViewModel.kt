package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.StatsFirebase
import com.example.rpregulator.models.Stats
import com.firebase.ui.database.FirebaseRecyclerOptions

class StatsViewModel(val lifecycleOwner: LifecycleOwner) : ViewModel() {


    private val _navigateToAddStats = MutableLiveData<Boolean?>()
    val navigateToAddStats: LiveData<Boolean?>
        get() = _navigateToAddStats

   val options = FirebaseRecyclerOptions.Builder<Stats>()
       .setQuery(StatsFirebase.databaseReference.orderByChild("sorting"), Stats::class.java)
       .setLifecycleOwner(lifecycleOwner)
       .build()

    fun navigateToAddStats() {
        _navigateToAddStats.value = true
        doneNavigateToAddStats()

    }

    private fun doneNavigateToAddStats() {
        _navigateToAddStats.value = null
    }


}