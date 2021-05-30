package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.BlessingsFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class AddBlessingViewModel(private val id: String) : ViewModel() {

    val blessingName = MutableLiveData<String?>()
    val blessingIntesity = MutableLiveData<String?>()
    val blessingDesc = MutableLiveData<String?>()
    var type = String()

    private val _navigateToBlessings = MutableLiveData<Boolean?>()
    val navigateToBlessings: LiveData<Boolean?>
        get() = _navigateToBlessings


    fun navigateToBlessings() {
        _navigateToBlessings.value = true
        doneNavigateToBlessings()

    }

    private fun doneNavigateToBlessings() {
        _navigateToBlessings.value = null
    }

    fun uploadData() {
        val databaseReference = BlessingsFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = CursesBlessingsHealth(
                entryID,
                blessingName.value,
                blessingIntesity.value,
                blessingDesc.value
        )
        viewModelScope.launch {
            BlessingsFirebase.uploadData(entryID, entry, id)
        }
    }

}