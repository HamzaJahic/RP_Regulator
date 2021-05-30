package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CharScreenViewModel : ViewModel() {

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
    val changePhoto: LiveData<Boolean?>
        get() = _changePhoto


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
        UsersFirebase.databaseReference.child(USER_ID.value!!).child("gold").setValue(newValue.toString())
        _gold.value = newValue.toString()
    }

    private fun getUserInfo() {

        UsersFirebase.databaseReference.child(USER_ID.value!!).addListenerForSingleValueEvent(object : ValueEventListener {
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


}