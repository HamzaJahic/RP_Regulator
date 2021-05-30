package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CharAdminViewModel(val id: String) : ViewModel() {

    private val _charName = MutableLiveData<String?>()
    val charName: LiveData<String?>
        get() = _charName

    private val _gold = MutableLiveData<String?>()
    val gold: LiveData<String?>
        get() = _gold


    private val _img = MutableLiveData<String?>()
    val img: LiveData<String?>
        get() = _img

    private val _changePhoto = MutableLiveData<Boolean?>()

    private val _navigateToSkills = MutableLiveData<String?>()
    val navigateToSkills: LiveData<String?>
        get() = _navigateToSkills
    private val _navigateToStats = MutableLiveData<String?>()
    val navigateToStats: LiveData<String?>
        get() = _navigateToStats

    private val _navigateToInventory = MutableLiveData<String?>()
    val navigateToInventory: LiveData<String?>
        get() = _navigateToInventory

    private val _navigateToBlessings = MutableLiveData<String?>()
    val navigateToBlessings: LiveData<String?>
        get() = _navigateToBlessings

    private val _navigateToCurses = MutableLiveData<String?>()
    val navigateToCurses: LiveData<String?>
        get() = _navigateToCurses

    private val _navigateToStatus = MutableLiveData<String?>()
    val navigateToStatus: LiveData<String?>
        get() = _navigateToStatus

    private val _navigateToXP = MutableLiveData<String?>()
    val navigateToXP: LiveData<String?>
        get() = _navigateToXP

    init {
        getUserInfo()
    }

    fun changePhoto() {
        _changePhoto.value = true
        doneChangePhoto()
    }

    private fun doneChangePhoto() {
        _changePhoto.value = null
    }


    fun increaseGold(inc: Int) {
        val oldValue = _gold.value!!.toInt()
        val newValue = oldValue + inc
        UsersFirebase.databaseReference.child(id).child("gold").setValue(newValue.toString())
        _gold.value = newValue.toString()
    }

    private fun getUserInfo() {

        UsersFirebase.databaseReference.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _charName.value = snapshot.child("username").value.toString()
                _gold.value = snapshot.child("gold").value.toString()
                _img.value = snapshot.child("img").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }


    fun navigateToSkills() {
        _navigateToSkills.value = id
        doneNavigateToSkills()
    }

    private fun doneNavigateToSkills() {
        _navigateToSkills.value = null
    }

    fun navigateToStats() {
        _navigateToStats.value = id
        doneNavigateToStats()
    }

    private fun doneNavigateToStats() {
        _navigateToStats.value = null
    }


    fun navigateToInventory() {
        _navigateToInventory.value = id
        doneNavigateToInventory()
    }

    private fun doneNavigateToInventory() {
        _navigateToInventory.value = null
    }

    fun navigateToBlessings() {
        _navigateToBlessings.value = id
        doneNavigateToBlessings()
    }

    private fun doneNavigateToBlessings() {
        _navigateToBlessings.value = null
    }

    fun navigateToCurses() {
        _navigateToCurses.value = id
        doneNavigateToCurses()
    }

    private fun doneNavigateToCurses() {
        _navigateToCurses.value = null
    }

    fun navigateToStatus() {
        _navigateToStatus.value = id
        doneNavigateToStatus()
    }

    private fun doneNavigateToStatus() {
        _navigateToStatus.value = null
    }

    fun navigateToXP() {
        _navigateToXP.value = id
        doneNavigateToXP()
    }

    private fun doneNavigateToXP() {
        _navigateToXP.value = null
    }


}