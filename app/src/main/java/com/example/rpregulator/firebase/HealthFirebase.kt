package com.example.rpregulator.firebase

import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HealthFirebase {

    companion object {
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users").child(USER_ID.value!!).child("health")

        fun uploadData(key: String, entry: CursesBlessingsHealth, id: String) {
            UsersFirebase.databaseReference.child(id).child("health").child(key).setValue(entry)

        }
    }

}