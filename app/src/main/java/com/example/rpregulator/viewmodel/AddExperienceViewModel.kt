package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Stats
import kotlinx.coroutines.launch

class AddExperienceViewModel(val id: String) : ViewModel() {

    val entryName = MutableLiveData<String?>()
    val entryValue = MutableLiveData<String?>()
    val entryDesc = MutableLiveData<String?>()


    private val _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
        get() = _navigateToMain

    fun navigateToMain() {
        _navigateToMain.value = true
        doneNavigateToMain()

    }

    private fun doneNavigateToMain() {
        _navigateToMain.value = null
    }


    fun uploadExperienceData() {
        val databaseReference = UsersFirebase.databaseReference.child(id).child("experience")
        val entryID = databaseReference.push().key.toString()
        val entry = Stats(
            entryID,
            entryName.value,
            "0",
            entryDesc.value,
            ""
        )
        viewModelScope.launch {
            UsersFirebase.uploadData(entryID, entry, id)
        }

        navigateToMain()
    }

}