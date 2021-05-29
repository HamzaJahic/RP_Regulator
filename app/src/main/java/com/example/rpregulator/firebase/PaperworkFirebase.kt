package com.example.rpregulator.firebase

import com.example.rpregulator.models.PaperWork
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PaperworkFirebase {

    companion object{
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users").child(USER_ID.value!!).child("paperwork")

        fun uploadData(key: String, entry: PaperWork){
            databaseReference.child(key).setValue(entry)

        }
    }

}