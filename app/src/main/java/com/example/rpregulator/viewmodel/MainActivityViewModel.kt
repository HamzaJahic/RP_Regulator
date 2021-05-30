package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainActivityViewModel(context: Context) : ViewModel() {


    private val _imgUri = MutableLiveData<String?>()
    val imgUri: LiveData<String?>
        get() = _imgUri

    init {
        USER_ID.value = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("id", null)
        getImg()
    }

    private fun getImg() {

        val img = UsersFirebase.databaseReference.child(USER_ID.value.toString())

        img.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.let {
                    _imgUri.value = it.child("img").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}