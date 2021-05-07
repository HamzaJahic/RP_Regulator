package com.example.rpregulator.firebase

import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.viewmodel.MainActivityViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HealthFirebase {

    companion object{
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users").child(MainActivityViewModel.id.value.toString()).child("health")

        fun uploadData(key: String, entry: CursesBlessingsHealth){
            databaseReference.child(key).setValue(entry)

        }
    }

}