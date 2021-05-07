package com.example.rpregulator.firebase

import com.example.rpregulator.models.Notes
import com.example.rpregulator.viewmodel.MainActivityViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NotesFirebase {

    companion object {
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users").child(MainActivityViewModel.id.value.toString()).child("notes")
        suspend fun uploadData(key: String, entry: Notes) {
            databaseReference.child(key).setValue(entry)
        }
    }
}