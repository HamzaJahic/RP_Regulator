package com.example.rpregulator.firebase

import com.example.rpregulator.models.Stats
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object StatsFirebase {

    val databaseReference: DatabaseReference =
        Firebase.database.reference.child("Users").child(USER_ID.value!!).child("stats")

    fun uploadData(key: String, entry: Stats, id: String) {
        UsersFirebase.databaseReference.child(id).child("stats").child(key).setValue(entry)

    }
}

