package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CursesFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class AddCurseViewModel(private val id: String) : ViewModel() {

    val curseName = MutableLiveData<String?>()
    val curseIntesity = MutableLiveData<String?>()
    val curseDesc = MutableLiveData<String?>()
    var type = String()

    private val _navigateToCurses = MutableLiveData<Boolean?>()
    val navigateToCurses: LiveData<Boolean?>
        get() = _navigateToCurses


    fun navigateToCurses() {
        _navigateToCurses.value = true
        doneNavigateToCurses()

    }

    private fun doneNavigateToCurses() {
        _navigateToCurses.value = null
    }

    fun uploadData() {
        val databaseReference = CursesFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = CursesBlessingsHealth(
            entryID,
            curseName.value,
            curseIntesity.value,
            curseDesc.value
        )
        viewModelScope.launch {
            CursesFirebase.uploadData(entryID, entry, id)
        }
    }

}