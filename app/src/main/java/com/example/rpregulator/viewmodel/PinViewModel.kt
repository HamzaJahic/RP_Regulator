package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class PinViewModel(user: User, context: Context) : ViewModel() {
    val pin = MutableLiveData<String?>()
    val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    val user = user
    val userName = user.username.toString()
    val _showError = MutableLiveData<Boolean?>()
    val showError: LiveData<Boolean?> get() = _showError

    val _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
            get() = _navigateToMain

    val _navigateToAdmin = MutableLiveData<Boolean?>()
    val navigateToAdmin: LiveData<Boolean?>
        get() = _navigateToAdmin

    fun loginUser(){
        val userRef = UsersFirebase.databaseReference.child(userName)
        viewModelScope.launch {
            userRef.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot?.let {
                        val pinDatabase = it.child("password").getValue().toString()

                        if(pin.value.toString() == pinDatabase){
                            navigateToMain()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }



    fun navigateToMain(){
        _navigateToMain.value = true
        editor.apply {
            putString("id", user.id)
            putBoolean("token", true)
        }.apply()
        doneNavigateToMain()
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
    }

    fun showError() {
        _showError.value = true

    }

    fun endShowError() {
        _showError.value = null
    }
}