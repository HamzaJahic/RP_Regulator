package com.example.rpregulator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.UsersFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class ChangePinViewModel() : ViewModel() {


    val oldPin = MutableLiveData<String?>()
    val newPin = MutableLiveData<String?>()
    val newPinAgain = MutableLiveData<String?>()
    var pin = String()


    init{
        getPass()
    }

    fun getPass(){
        val userRef = UsersFirebase.databaseReference.child(MainActivityViewModel.id.value.toString())
        viewModelScope.launch {
            userRef.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot?.let {
                        pin = it.child("password").getValue().toString()


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }


    fun changePass(){
        if(oldPin.value == pin && newPin.value==newPinAgain.value){
            UsersFirebase.databaseReference.child(MainActivityViewModel.id.value.toString()).child("password").setValue(newPin.value)
        }
    }

}