package com.example.rpregulator.firebase

import com.example.rpregulator.models.Skills
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SkillsFirebase {

    companion object {
        val databaseReference: DatabaseReference =
            Firebase.database.reference.child("Users").child(USER_ID.value!!).child("skills")

        fun uploadData(key: String, entry: Skills, id: String) {
            UsersFirebase.databaseReference.child(id).child("skills").child(key).setValue(entry)
        }
    }
}