package com.example.rpregulator.firebase

import com.example.rpregulator.models.Chars
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CharsFirebase {

    companion object{
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Chars and Monsters").child("Chars")

        fun uploadData(key: String, entry: Chars){
            databaseReference.child(key).setValue(entry)
        }
    }

}