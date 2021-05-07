package com.example.rpregulator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.BlessingsFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import kotlinx.coroutines.launch

class AddBlessingViewModel() : ViewModel() {

    val blessingName = MutableLiveData<String?>()
    val blessingIntesity = MutableLiveData<String?>()
    val blessingDesc = MutableLiveData<String?>()
    var type = String()


    init {
        type = "ACTIVE"
    }



  private val _navigateToBlessings = MutableLiveData<Boolean?>()
    val navigateToBlessings: LiveData<Boolean?>
    get() = _navigateToBlessings


    fun navigateToBlessings(){
        _navigateToBlessings.value = true
        doneNavigateToBlessings()
      
    }

    fun doneNavigateToBlessings(){
        _navigateToBlessings.value = null
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
        val databaseReference = BlessingsFirebase.databaseReference
        val entryID= databaseReference.push().key.toString()
        val entry = CursesBlessingsHealth(
              entryID,
                blessingName.value,
                blessingIntesity.value,
                blessingDesc.value
        )
        viewModelScope.launch {
            BlessingsFirebase.uploadData(entryID, entry)
        }
    }

}