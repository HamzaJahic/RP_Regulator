package com.example.rpregulator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CreaturesFirebase
import com.example.rpregulator.models.Creatures
import kotlinx.coroutines.launch

class CreatureDetailsViewModel(creature: Creatures) : ViewModel() {

    val creatureName = MutableLiveData<String?>()
    val creatureApperance = MutableLiveData<String?>()
    val creatureDesc = MutableLiveData<String?>()
    var id = String()
    var img = String()
    var creature = creature


    init {
        creatureName.value = creature.name
        creatureApperance.value = creature.ark
        creatureDesc.value = creature.desc
        id = creature.id!!
        img= creature.img!!
    }



  private val _navigateToCreatureEdit = MutableLiveData<Creatures?>()
    val navigateToCreatureEdit: LiveData<Creatures?>
    get() = _navigateToCreatureEdit

    private val _navigateToCreature = MutableLiveData<Boolean?>()
    val navigateToCreature: LiveData<Boolean?>
        get() = _navigateToCreature

    private val _showAlertDialog = MutableLiveData<Boolean?>()
    val showAlertDialog: LiveData<Boolean?>
        get() = _showAlertDialog

    private val _uploadPhoto = MutableLiveData<Boolean?>()
    val uploadPhoto: LiveData<Boolean?>
        get() = _uploadPhoto


    fun navigateToCreatureEdit(){
        _navigateToCreatureEdit.value = creature
        doneNavigateToCreatureEdit()

    }

    fun doneNavigateToCreatureEdit(){
        _navigateToCreatureEdit.value = null
    }

    fun navigateToCreature(){
        _navigateToCreature.value = true
        doneNavigateToCreature()
      
    }

    fun doneNavigateToCreature(){
        _navigateToCreature.value = null
    }



    fun editData(){
        val databaseReference = CreaturesFirebase.databaseReference
        val entryID= id
        val entry = Creatures(
                entryID,
                img,
                creatureName.value,
                creatureApperance.value,
                creatureDesc.value
        )
        viewModelScope.launch {
            CreaturesFirebase.uploadData(entryID, entry)
            Log.d("Img", "${img}")
            CreaturesFirebase.databaseReference.child(creature.id!!).child("img").setValue(img)
        }
    }

    fun deleteEntry(){
        val entryID = id
        viewModelScope.launch{
            CreaturesFirebase.databaseReference.child(entryID).removeValue()
            navigateToCreature()
        }
    }

    fun showAlertDialog() {
        _showAlertDialog.value = true
        doneShow()
    }

    fun doneShow() {
        _showAlertDialog.value = null
    }


    fun uploadPhoto(){
        _uploadPhoto.value = true
        doneUploadPhoto()

    }

    fun doneUploadPhoto(){
        _uploadPhoto.value = null
    }

}