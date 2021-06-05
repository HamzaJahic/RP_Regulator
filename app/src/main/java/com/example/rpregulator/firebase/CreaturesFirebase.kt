package com.example.rpregulator.firebase

import com.example.rpregulator.models.Creatures
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object CreaturesFirebase {

    val databaseReference: DatabaseReference =
        Firebase.database.reference.child("Chars and Monsters").child("Creatures")

    fun uploadData(key: String, entry: Creatures) {
        databaseReference.child(key).setValue(entry)

    }
}

