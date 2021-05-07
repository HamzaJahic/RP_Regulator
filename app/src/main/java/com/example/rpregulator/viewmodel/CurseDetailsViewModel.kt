package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CursesFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class CurseDetailsViewModel(curse: CursesBlessingsHealth) : ViewModel() {

    val curseName = MutableLiveData<String?>()
    val curseIntensity = MutableLiveData<String?>()
    val curseDesc = MutableLiveData<String?>()
    var id = String()
    var curse = curse


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


    fun navigateToCurseEdit(){
        _navigateToCurseEdit.value = curse
        doneNavigateToCurseEdit()

    }

    fun doneNavigateToCurseEdit(){
        _navigateToCurseEdit.value = null
    }

    fun navigateToCurse(){
        _navigateToCurse.value = true
        doneNavigateToCurse()
      
    }

    fun doneNavigateToCurse(){
        _navigateToCurse.value = null
    }



    fun editData(){
        val databaseReference = CursesFirebase.databaseReference
        val entryID= id
        val entry = CursesBlessingsHealth(
                entryID,
                curseName.value,
                curseIntensity.value,
                curseDesc.value
        )
        viewModelScope.launch {
            CursesFirebase.uploadData(entryID, entry)
        }
    }

    fun deleteEntry(){
        val entryID = id
        viewModelScope.launch{
            CursesFirebase.databaseReference.child(entryID).removeValue()
            navigateToCurse()
        }
    }

    fun showAlertDialog() {
        _showAlertDialog.value = true
        doneShow()
    }

    fun doneShow() {
        _showAlertDialog.value = null
    }

}