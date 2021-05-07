package com.example.rpregulator.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UsersFirebase {

    companion object{
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users")

    }
}