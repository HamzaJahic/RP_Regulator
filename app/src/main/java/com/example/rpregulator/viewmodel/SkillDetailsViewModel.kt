package com.example.rpregulator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.SkillsFirebase
import com.example.rpregulator.models.Skills
import kotlinx.coroutines.launch

class SkillDetailsViewModel(skill: Skills) : ViewModel() {

    val skillName = MutableLiveData<String?>()
    val skillCost = MutableLiveData<String?>()
    val skillDesc = MutableLiveData<String?>()
    var type = String()
    var id = String()
    var skill = skill


    init {
        skillName.value = skill.name
        skillCost.value = skill.cost
        skillDesc.value = skill.desc
        type = skill.type!!
        id = skill.id!!
    }



  private val _navigateToSkillEdit = MutableLiveData<Skills?>()
    val navigateToSkillEdit: LiveData<Skills?>
    get() = _navigateToSkillEdit

    private val _navigateToSkills = MutableLiveData<Boolean?>()
    val navigateToSkills: LiveData<Boolean?>
        get() = _navigateToSkills

    private val _showAlertDialog = MutableLiveData<Boolean?>()
    val showAlertDialog: LiveData<Boolean?>
        get() = _showAlertDialog


    fun navigateToSkillEdit(){
        _navigateToSkillEdit.value = skill
        doneNavigateToSkillEdit()

    }

    fun doneNavigateToSkillEdit(){
        _navigateToSkillEdit.value = null
    }

    fun navigateToSkills(){
        _navigateToSkills.value = true
        doneNavigateToSkills()
      
    }

    fun doneNavigateToSkills(){
        _navigateToSkills.value = null
    }

    fun setTypeToActive(){
        type = "ACTIVE"
        Log.d("Type", type)
    }
    fun setTypeToPassive(){
        type = "PASSIVE"
        Log.d("Type",type)
    }

    fun editData(){
        val databaseReference = SkillsFirebase.databaseReference
        val entryID= id
        val entry = Skills(
                entryID,
                skillName.value,
                type,
                skillCost.value,
                skillDesc.value,
            skill.value
        )
        viewModelScope.launch {
            SkillsFirebase.uploadData(entryID, entry)
        }
    }

    fun deleteEntry(){
        val entryID = id
        viewModelScope.launch{
            SkillsFirebase.databaseReference.child(entryID).removeValue()
            navigateToSkills()
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