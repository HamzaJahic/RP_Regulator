package com.example.rpregulator.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.rpregulator.firebase.CursesFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class AddCurseViewModel() : ViewModel() {

    val curseName = MutableLiveData<String?>()
    val curseIntesity = MutableLiveData<String?>()
    val curseDesc = MutableLiveData<String?>()
    var type = String()


    init {
        type = "ACTIVE"
    }



  private val _navigateToCurses = MutableLiveData<Boolean?>()
    val navigateToCurses: LiveData<Boolean?>
    get() = _navigateToCurses


    fun navigateToCurses(){
        _navigateToCurses.value = true
        doneNavigateToCurses()
      
    }

    fun doneNavigateToCurses(){
        _navigateToCurses.value = null
    }

    fun setTypeToActive(){
        type = "ACTIVE"
        Log.d("Type", type)
    }
    fun setTypeToPassive(){
        type = "PASSIVE"
        Log.d("Type",type)
    }

    fun uploadData(){
        val databaseReference = CursesFirebase.databaseReference
        val entryID= databaseReference.push().key.toString()
        val entry = CursesBlessingsHealth(
              entryID,
                curseName.value,
                curseIntesity.value,
                curseDesc.value
        )
        viewModelScope.launch {
            CursesFirebase.uploadData(entryID, entry)
        }
    }

}