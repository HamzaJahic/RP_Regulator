package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CharScreenViewModel() : ViewModel() {

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
        UsersFirebase.databaseReference.child(MainActivityViewModel.id.value.toString()).child("gold").setValue(newValue.toString())
        _gold.value = newValue.toString()
    }

    fun getUserInfo(){

        UsersFirebase.databaseReference.child(MainActivityViewModel.id.value.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
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






}