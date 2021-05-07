package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.models.Inventory
import com.example.rpregulator.models.Skills
import com.example.rpregulator.models.Stats
import com.firebase.ui.database.FirebaseRecyclerOptions

class DataViewModel(lifecycleOwner: LifecycleOwner, id: String) : ViewModel() {


    private val _navigateToAdd = MutableLiveData<Boolean?>()
    val navigateToAdd: LiveData<Boolean?>
    get() = _navigateToAdd

    val optionsStats = FirebaseRecyclerOptions.Builder<Stats>()
            .setQuery(UsersFirebase.databaseReference.child(id).child("stats"), Stats::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()

    val optionsSkills = FirebaseRecyclerOptions.Builder<Skills>()
            .setQuery(UsersFirebase.databaseReference.child(id).child("skills"), Skills::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()

    val optionsInventory = FirebaseRecyclerOptions.Builder<Inventory>()
            .setQuery(UsersFirebase.databaseReference.child(id).child("inventory"), Inventory::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()

    val optionsCurses = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
            .setQuery(UsersFirebase.databaseReference.child(id).child("curses"), CursesBlessingsHealth::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()

    val optionsBlessings = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
            .setQuery(UsersFirebase.databaseReference.child(id).child("blessings"), CursesBlessingsHealth::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()

    val optionsStatus = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
            .setQuery(UsersFirebase.databaseReference.child(id).child("status"), CursesBlessingsHealth::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()


    fun navigateToAdd(){
        _navigateToAdd.value = true
        doneNavigateToAdd()
      
    }

    fun doneNavigateToAdd(){
        _navigateToAdd.value = null
    }


}