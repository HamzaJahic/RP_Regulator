package com.example.rpregulator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.SkillsFirebase
import com.example.rpregulator.models.Skills
import kotlinx.coroutines.launch

class AddSkillViewModel() : ViewModel() {

    val skillName = MutableLiveData<String?>()
    val skillCost = MutableLiveData<String?>()
    val skillDesc = MutableLiveData<String?>()
    var type = String()


    init {
        type = "ACTIVE"
    }



  private val _navigateToSkills = MutableLiveData<Boolean?>()
    val navigateToSkills: LiveData<Boolean?>
    get() = _navigateToSkills


    fun navigateToSkills(){
        skillName.value=""
        skillCost.value = ""
        skillDesc.value = ""
      
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
        val databaseReference = SkillsFirebase.databaseReference
        val entryID= databaseReference.push().key.toString()
        val entry = Skills(
                entryID,
                skillName.value,
                type,
                skillCost.value,
                skillDesc.value,
            "1"

        )
        viewModelScope.launch {
            SkillsFirebase.uploadData(entryID, entry)
        }
    }

}