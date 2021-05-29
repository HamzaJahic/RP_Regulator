package com.example.rpregulator.firebase

import com.example.rpregulator.models.Inventory
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class InventoryFirebase {

    companion object{
        val databaseReference: DatabaseReference = Firebase.database.reference.child("Users").child(USER_ID.value!!).child("inventory")

        suspend fun uploadData(key: String, entry: Inventory){
            databaseReference.child(key).child("id").setValue(entry.id)
            databaseReference.child(key).child("name").setValue(entry.name)
            databaseReference.child(key).child("value").setValue(entry.value)
            databaseReference.child(key).child("desc").setValue(entry.desc)
        }
    }

}