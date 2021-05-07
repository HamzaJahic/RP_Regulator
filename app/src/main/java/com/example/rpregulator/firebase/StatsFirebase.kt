package com.example.rpregulator.firebase

import com.example.rpregulator.models.Stats
import com.example.rpregulator.viewmodel.MainActivityViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StatsFirebase {

    companion object{
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users").child(MainActivityViewModel.id.value.toString()).child("stats")

        fun uploadData(key: String, entry: Stats){
            databaseReference.child(key).setValue(entry)

        }
    }

}