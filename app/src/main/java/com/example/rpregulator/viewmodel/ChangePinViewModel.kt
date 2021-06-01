package com.example.rpregulator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class ChangePinViewModel : ViewModel() {


    val oldPin = MutableLiveData<String?>()
    val newPin = MutableLiveData<String?>()
    val newPinAgain = MutableLiveData<String?>()
    var pin = String()


    init {
        getPass()
    }

    private fun getPass() {
        val userRef = UsersFirebase.databaseReference.child(USER_ID.value!!)
        viewModelScope.launch {
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.let {
                        pin = it.child("password").value.toString()


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }


    fun changePass() {
        if (oldPin.value == pin && newPin.value == newPinAgain.value) {
            UsersFirebase.databaseReference.child(USER_ID.value!!).child("password")
                .setValue(newPin.value)
        }
    }

}