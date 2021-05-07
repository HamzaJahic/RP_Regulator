package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.InventoryFirebase
import com.example.rpregulator.models.Inventory
import kotlinx.coroutines.launch

class InventoryDetailsViewModel(inventory: Inventory) : ViewModel() {

    val inventoryName = MutableLiveData<String?>()
    val inventoryQuantity = MutableLiveData<String?>()
    val inventoryDesc = MutableLiveData<String?>()
    var id = String()
    var inventory = inventory


    init {
        inventoryName.value = inventory.name
        inventoryQuantity.value = inventory.value
        inventoryDesc.value = inventory.desc
        id = inventory.id!!
    }



  private val _navigateToInventoryEdit = MutableLiveData<Inventory?>()
    val navigateToInventoryEdit: LiveData<Inventory?>
    get() = _navigateToInventoryEdit

    private val _navigateToInventory = MutableLiveData<Boolean?>()
    val navigateToInventory: LiveData<Boolean?>
        get() = _navigateToInventory

    private val _showAlertDialog = MutableLiveData<Boolean?>()
    val showAlertDialog: LiveData<Boolean?>
        get() = _showAlertDialog


    fun navigateToInventoryEdit(){
        _navigateToInventoryEdit.value = inventory
        doneNavigateToInventoryEdit()

    }

    fun doneNavigateToInventoryEdit(){
        _navigateToInventoryEdit.value = null
    }

    fun navigateToInventory(){
        _navigateToInventory.value = true
        doneNavigateToInventory()
      
    }

    fun doneNavigateToInventory(){
        _navigateToInventory.value = null
    }



    fun editData(){
        val databaseReference = InventoryFirebase.databaseReference
        val entryID= id
        val entry = Inventory(
                entryID,
                inventoryName.value,
                inventoryQuantity.value,
                inventoryDesc.value
        )
        viewModelScope.launch {
            InventoryFirebase.uploadData(entryID, entry)
        }
    }

    fun deleteEntry(){
        val entryID = id
        viewModelScope.launch{
            InventoryFirebase.databaseReference.child(entryID).removeValue()
            navigateToInventory()
        }
    }

    fun showAlertDialog() {
        _showAlertDialog.value = true
        doneShow()
    }

    fun doneShow() {
        _showAlertDialog.value = null
    }

}