package com.example.rpregulator.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainActivityViewModel(context: Context) : ViewModel() {

    companion object{

        private val _id = MutableLiveData<String>()
        val id : LiveData<String>
            get() = _id

    }
    private val _imgUri = MutableLiveData<String?>()
    val imgUri : LiveData<String?>
        get() = _imgUri
    val idUser = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("id", null)
    val admin = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getBoolean("admin", false)

    init {
        _id.value = idUser
        getImg()
    }

    fun getImg(){

        val img = UsersFirebase.databaseReference.child(id.value.toString())

        img.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot?.let {
                    _imgUri.value = it.child("img").getValue().toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}