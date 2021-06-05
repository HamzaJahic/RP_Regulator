package com.example.rpregulator.firebase

import com.example.rpregulator.models.Stats
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object UsersFirebase {

    val databaseReference: DatabaseReference = Firebase.database.reference.child("Users")
    fun uploadData(key: String, entry: Stats, id: String) {
        databaseReference.child(id).child("experience").child(key).setValue(entry)

    }
}
