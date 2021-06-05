package com.example.rpregulator.firebase

import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object CursesFirebase {

    val databaseReference: DatabaseReference =
        Firebase.database.reference.child("Users").child(USER_ID.value!!).child("curses")

    fun uploadData(key: String, entry: CursesBlessingsHealth, id: String) {
        UsersFirebase.databaseReference.child(id).child("curses").child(key).setValue(entry)

    }
}
