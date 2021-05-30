package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.CreaturesFirebase
import com.example.rpregulator.models.Creatures
import com.firebase.ui.database.FirebaseRecyclerOptions

class CreaturesViewModel(lifecycleOwner: LifecycleOwner) : ViewModel() {


    private val _navigateToAddCreatures = MutableLiveData<Boolean?>()
    val navigateToAddCreatures: LiveData<Boolean?>
        get() = _navigateToAddCreatures

    private val _navigateToCreaturesDetails = MutableLiveData<Creatures?>()
    val navigateToCreaturesDetails: LiveData<Creatures?>
        get() = _navigateToCreaturesDetails

    val options = FirebaseRecyclerOptions.Builder<Creatures>()
            .setQuery(CreaturesFirebase.databaseReference.orderByChild("name"), Creatures::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()


    fun navigateToCreaturesDetails(Creatures: Creatures) {
        _navigateToCreaturesDetails.value = Creatures
        doneNavigateToCreaturesDetails()

    }

    fun doneNavigateToCreaturesDetails() {
        _navigateToCreaturesDetails.value = null
    }

    fun navigateToAddCreatures() {
        _navigateToAddCreatures.value = true
        doneNavigateToAddCreatures()

    }

    fun doneNavigateToAddCreatures() {
        _navigateToAddCreatures.value = null
    }


}