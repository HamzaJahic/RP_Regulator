package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CursesFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import kotlinx.coroutines.launch

class CurseDetailsViewModel(var curse: CursesBlessingsHealth) : ViewModel() {

    val curseName = MutableLiveData<String?>()
    val curseIntensity = MutableLiveData<String?>()
    val curseDesc = MutableLiveData<String?>()
    var id = String()

    init {
        curseName.value = curse.name
        curseIntensity.value = curse.value
        curseDesc.value = curse.desc
        id = curse.id!!
    }


    private val _navigateToCurseEdit = MutableLiveData<CursesBlessingsHealth?>()
    val navigateToCurseEdit: LiveData<CursesBlessingsHealth?>
        get() = _navigateToCurseEdit

    private val _navigateToCurse = MutableLiveData<Boolean?>()
    val navigateToCurse: LiveData<Boolean?>
        get() = _navigateToCurse

    private val _showAlertDialog = MutableLiveData<Boolean?>()
    val showAlertDialog: LiveData<Boolean?>
        get() = _showAlertDialog


    fun navigateToCurseEdit() {
        _navigateToCurseEdit.value = curse
        doneNavigateToCurseEdit()

    }

    private fun doneNavigateToCurseEdit() {
        _navigateToCurseEdit.value = null
    }

    private fun navigateToCurse() {
        _navigateToCurse.value = true
        doneNavigateToCurse()

    }

    private fun doneNavigateToCurse() {
        _navigateToCurse.value = null
    }

    fun editData() {
        CursesFirebase.databaseReference
        val entryID = id
        val entry = CursesBlessingsHealth(
            entryID,
            curseName.value,
            curseIntensity.value,
            curseDesc.value
        )
        viewModelScope.launch {
            CursesFirebase.uploadData(entryID, entry, USER_ID.value!!)
        }
    }

    fun deleteEntry() {
        val entryID = id
        viewModelScope.launch {
            CursesFirebase.databaseReference.child(entryID).removeValue()
            navigateToCurse()
        }
    }

    fun showAlertDialog() {
        _showAlertDialog.value = true
        doneShow()
    }

    private fun doneShow() {
        _showAlertDialog.value = null
    }

}