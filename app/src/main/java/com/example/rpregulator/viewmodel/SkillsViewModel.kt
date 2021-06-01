package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.SkillsFirebase
import com.example.rpregulator.models.Skills
import com.firebase.ui.database.FirebaseRecyclerOptions

class SkillsViewModel(lifecycleOwner: LifecycleOwner) : ViewModel() {


    val options = FirebaseRecyclerOptions.Builder<Skills>()
        .setQuery(SkillsFirebase.databaseReference.orderByChild("name"), Skills::class.java)
        .setLifecycleOwner(lifecycleOwner)
        .build()


    private val _navigateToAddSkills = MutableLiveData<Boolean?>()
    val navigateToAddSkills: LiveData<Boolean?>
        get() = _navigateToAddSkills

    private val _navigateToSkillDetails = MutableLiveData<Skills?>()
    val navigateToSkillDetails: LiveData<Skills?>
        get() = _navigateToSkillDetails


    fun navigateToSkillDetails(skills: Skills) {
        _navigateToSkillDetails.value = skills
        doneNavigateToSkillDetails()

    }

    private fun doneNavigateToSkillDetails() {
        _navigateToSkillDetails.value = null
    }


    fun navigateToAddSkills() {
        _navigateToAddSkills.value = true
        doneNavigateToAddSkills()

    }

    private fun doneNavigateToAddSkills() {
        _navigateToAddSkills.value = null
    }


}