package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.InventoryFirebase
import com.example.rpregulator.models.Inventory
import kotlinx.coroutines.launch

class AddInventoryViewModel(private val id: String) : ViewModel() {


    val inventoryName = MutableLiveData<String?>()
    val inventoryValue = MutableLiveData<String?>()
    val inventoryDesc = MutableLiveData<String?>()


    private val _navigateToInventory = MutableLiveData<Boolean?>()
    val navigateToInventory: LiveData<Boolean?>
        get() = _navigateToInventory


    fun navigateToInventory() {
        _navigateToInventory.value = true
        doneNavigateToInventory()

    }

    fun doneNavigateToInventory() {
        _navigateToInventory.value = null
    }

    fun uploadData() {
        val databaseReference = InventoryFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()

        val entry = Inventory(
            entryID,
            inventoryName.value,
            inventoryValue.value,
            inventoryDesc.value
        )

        viewModelScope.launch {
            InventoryFirebase.uploadData(entryID, entry, id)
        }
    }


}