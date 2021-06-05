package com.example.rpregulator.firebase

import com.example.rpregulator.models.Inventory
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object InventoryFirebase {

    val databaseReference: DatabaseReference =
        Firebase.database.reference.child("Users").child(USER_ID.value!!).child("inventory")

    fun uploadData(key: String, entry: Inventory, id: String) {
        UsersFirebase.databaseReference.child(id).child("inventory").child(key).setValue(entry)
    }
}

