package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CharAdminViewModel(id : String) : ViewModel() {

    val id = id
    private val _charName = MutableLiveData<String?>()
    val charName : LiveData<String?>
        get() = _charName

    private val _gold = MutableLiveData<String?>()
    val gold : LiveData<String?>
    get() = _gold


    private val _img = MutableLiveData<String?>()
    val img: LiveData<String?>
        get() = _img

    private val _changePhoto = MutableLiveData<Boolean?>()
    val changePhoto: LiveData<Boolean?>
        get() = _changePhoto

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

    fun changePhoto(){
        _changePhoto.value = true
        doneChangePhoto()
    }

    fun doneChangePhoto(){
        _changePhoto.value = null
    }


    fun increaseGold(inc : Int){
        var oldValue = _gold.value!!.toInt()
        var newValue = oldValue+inc
        UsersFirebase.databaseReference.child(id).child("gold").setValue(newValue.toString())
        _gold.value = newValue.toString()
    }

    fun getUserInfo(){

        UsersFirebase.databaseReference.child(id).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                _charName.value = snapshot.child("username").getValue().toString()
                _gold.value = snapshot.child("gold").getValue().toString()
                _img.value = snapshot.child("img").getValue().toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        } )

    }


    fun navigateToSkills(){
        _navigateToSkills.value = id
        doneNavigateToSkills()
    }

    fun doneNavigateToSkills(){
        _navigateToSkills.value = null
    }

    fun navigateToStats(){
        _navigateToStats.value = id
        doneNavigateToStats()
    }

    fun doneNavigateToStats(){
        _navigateToStats.value = null
    }


    fun navigateToInventory(){
        _navigateToInventory.value = id
        doneNavigateToInventory()
    }

    fun doneNavigateToInventory(){
        _navigateToInventory.value = null
    }

    fun navigateToBlessings(){
        _navigateToBlessings.value = id
        doneNavigateToBlessings()
    }

    fun doneNavigateToBlessings(){
        _navigateToBlessings.value = null
    }

    fun navigateToCurses(){
        _navigateToCurses.value = id
        doneNavigateToCurses()
    }

    fun doneNavigateToCurses(){
        _navigateToCurses.value = null
    }

    fun navigateToStatus(){
        _navigateToStatus.value = id
        doneNavigateToStatus()
    }

    fun doneNavigateToStatus(){
        _navigateToStatus.value = null
    }

    fun navigateToXP(){
        _navigateToXP.value = id
        doneNavigateToXP()
    }

    fun doneNavigateToXP(){
        _navigateToXP.value = null
    }





}