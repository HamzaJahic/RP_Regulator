package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.HealthFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class AddHealthViewModel(private val id: String) : ViewModel() {

    val healthName = MutableLiveData<String?>()
    val healthIntesity = MutableLiveData<String?>()
    val healthDesc = MutableLiveData<String?>()
    var type = String()


    init {
        type = "ACTIVE"
    }


    private val _navigateToHealths = MutableLiveData<Boolean?>()
    val navigateToHealths: LiveData<Boolean?>
        get() = _navigateToHealths


    fun navigateToHealths() {
        _navigateToHealths.value = true
        doneNavigateToHealths()

    }

    private fun doneNavigateToHealths() {
        _navigateToHealths.value = null
    }


    fun uploadData() {
        val databaseReference = HealthFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = CursesBlessingsHealth(
                entryID,
                healthName.value,
                healthIntesity.value,
                healthDesc.value
        )
        viewModelScope.launch {
            HealthFirebase.uploadData(entryID, entry, id)
        }
    }

}