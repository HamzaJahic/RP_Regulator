package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.InventoryFirebase
import com.example.rpregulator.models.Inventory
import com.firebase.ui.database.FirebaseRecyclerOptions

class InventoryViewModel(val lifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _navigateToAddInventory = MutableLiveData<Boolean?>()
    val navigateToAddInventory: LiveData<Boolean?>
        get() = _navigateToAddInventory

    private val _navigateToInventoryDetails = MutableLiveData<Inventory?>()
    val navigateToInventoryDetails: LiveData<Inventory?>
        get() = _navigateToInventoryDetails

    val options = FirebaseRecyclerOptions.Builder<Inventory>()
        .setQuery(
            InventoryFirebase.databaseReference.orderByChild("sorting"),
            Inventory::class.java
        )
        .setLifecycleOwner(lifecycleOwner)
        .build()

    fun navigateToInventoryDetails(inventory: Inventory) {
        _navigateToInventoryDetails.value = inventory
        doneNavigateToInventoryDetails()

    }

    private fun doneNavigateToInventoryDetails() {
        _navigateToInventoryDetails.value = null
    }

    fun navigateToAddInventory() {
        _navigateToAddInventory.value = true
        doneNavigateToAddInventory()
    }

    private fun doneNavigateToAddInventory() {
        _navigateToAddInventory.value = null
    }


}