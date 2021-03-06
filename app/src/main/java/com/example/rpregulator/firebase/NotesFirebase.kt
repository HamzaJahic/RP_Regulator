package com.example.rpregulator.firebase

import com.example.rpregulator.models.Notes
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object NotesFirebase {

    val databaseReference: DatabaseReference =
        Firebase.database.reference.child("Users").child(USER_ID.value!!).child("notes")

    fun uploadData(key: String, entry: Notes) {
        databaseReference.child(key).setValue(entry)
    }
}
