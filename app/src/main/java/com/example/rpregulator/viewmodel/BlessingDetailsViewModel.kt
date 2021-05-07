package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.BlessingsFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class BlessingDetailsViewModel(blessing: CursesBlessingsHealth) : ViewModel() {

    val blessingName = MutableLiveData<String?>()
    val blessingIntensity = MutableLiveData<String?>()
    val blessingDesc = MutableLiveData<String?>()
    var id = String()
    var blessing = blessing


    init {
        blessingName.value = blessing.name
        blessingIntensity.value = blessing.value
        blessingDesc.value = blessing.desc
        id = blessing.id!!
    }



  private val _navigateToBlessingEdit = MutableLiveData<CursesBlessingsHealth?>()
    val navigateToBlessingEdit: LiveData<CursesBlessingsHealth?>
    get() = _navigateToBlessingEdit

    private val _navigateToBlessing = MutableLiveData<Boolean?>()
    val navigateToBlessing: LiveData<Boolean?>
        get() = _navigateToBlessing

    private val _showAlertDialog = MutableLiveData<Boolean?>()
    val showAlertDialog: LiveData<Boolean?>
        get() = _showAlertDialog


    fun navigateToBlessingEdit(){
        _navigateToBlessingEdit.value = blessing
        doneNavigateToBlessingEdit()

    }

    fun doneNavigateToBlessingEdit(){
        _navigateToBlessingEdit.value = null
    }

    fun navigateToBlessing(){
        _navigateToBlessing.value = true
        doneNavigateToBlessing()
      
    }

    fun doneNavigateToBlessing(){
        _navigateToBlessing.value = null
    }



    fun editData(){
        val databaseReference = BlessingsFirebase.databaseReference
        val entryID= id
        val entry = CursesBlessingsHealth(
                entryID,
                blessingName.value,
                blessingIntensity.value,
                blessingDesc.value
        )
        viewModelScope.launch {
            BlessingsFirebase.uploadData(entryID, entry)
        }
    }

    fun deleteEntry(){
        val entryID = id
        viewModelScope.launch{
            BlessingsFirebase.databaseReference.child(entryID).removeValue()
            navigateToBlessing()
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