package com.example.rpregulator.viewmodel

import android.content.Context
import android.content.SharedPreferences
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

class PinViewModel(val user: User, context: Context) : ViewModel() {
    val pin = MutableLiveData<String?>()
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()
    private val userName = user.username.toString()
    private val _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
        get() = _navigateToMain

    fun loginUser() {
        val userRef = UsersFirebase.databaseReference.child(userName)
        viewModelScope.launch {
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.let {
                        val pinDatabase = it.child("password").value.toString()

                        if (pin.value.toString() == pinDatabase) {
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

    fun navigateToMain() {
        _navigateToMain.value = true
        editor.apply {
            putString("id", user.id)
            putBoolean("token", true)
        }.apply()
        doneNavigateToMain()
    }

    private fun doneNavigateToMain() {
        _navigateToMain.value = null
    }

}