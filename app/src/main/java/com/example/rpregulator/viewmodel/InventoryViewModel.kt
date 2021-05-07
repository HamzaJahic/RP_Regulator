package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.models.Inventory

class InventoryViewModel() : ViewModel() {


    private val _navigateToAddInventory = MutableLiveData<Boolean?>()
    val navigateToAddInventory: LiveData<Boolean?>
    get() = _navigateToAddInventory

    private val _navigateToInventoryDetails = MutableLiveData<Inventory?>()
    val navigateToInventoryDetails: LiveData<Inventory?>
        get() = _navigateToInventoryDetails


    fun navigateToInventoryDetails(inventory: Inventory){
        _navigateToInventoryDetails.value = inventory
        doneNavigateToInventoryDetails()

    }

    fun doneNavigateToInventoryDetails(){
        _navigateToInventoryDetails.value = null
    }


    fun navigateToAddInventory(){
        _navigateToAddInventory.value = true
        doneNavigateToAddInventory()
      
    }

    fun doneNavigateToAddInventory(){
        _navigateToAddInventory.value = null
    }


}