package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.StatsFirebase
import com.example.rpregulator.models.Stats
import kotlinx.coroutines.launch

class AddStatViewModel() : ViewModel() {

    val statName = MutableLiveData<String?>()
    val statValue = MutableLiveData<String?>()
    val statDesc = MutableLiveData<String?>()


    private val _navigateToStats = MutableLiveData<Boolean?>()
    val navigateToStats: LiveData<Boolean?>
    get() = _navigateToStats


    fun navigateToStats(){
        _navigateToStats.value = true
        donenavigateToStats()
      
    }

    fun donenavigateToStats(){
        _navigateToStats.value = null
    }

    fun uploadData(){

        val databaseReference = StatsFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()

        val entry = Stats(
                entryID,
                statName.value,
                statValue.value,
                statDesc.value,
            ""
        )

        viewModelScope.launch {
            StatsFirebase.uploadData(entryID, entry)
        }

    }


}