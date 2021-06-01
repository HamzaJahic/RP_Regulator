package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CreaturesFirebase
import com.example.rpregulator.models.Creatures
import kotlinx.coroutines.launch

class AddCreatureViewModel : ViewModel() {

    val creatureName = MutableLiveData<String?>()
    val creatureArk = MutableLiveData<String?>()
    val creatureDesc = MutableLiveData<String?>()
    var img = String()


    private val _navigateToCreatures = MutableLiveData<Boolean?>()
    val navigateToCreatures: LiveData<Boolean?>
        get() = _navigateToCreatures

    private val _uploadPhoto = MutableLiveData<Boolean?>()
    val uploadPhoto: LiveData<Boolean?>
        get() = _uploadPhoto

    init {
        img = ""
    }


    fun navigateToCreatures() {
        _navigateToCreatures.value = true
        doneNavigateToCreatures()

    }

    private fun doneNavigateToCreatures() {
        _navigateToCreatures.value = null
    }

    fun uploadData() {
        val databaseReference = CreaturesFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = Creatures(
            entryID,
            img,
            creatureName.value,
            creatureArk.value,
            creatureDesc.value
        )
        viewModelScope.launch {
            CreaturesFirebase.uploadData(entryID, entry)
        }
    }

    fun uploadPhoto() {
        _uploadPhoto.value = true
        doneUploadPhoto()

    }

    private fun doneUploadPhoto() {
        _uploadPhoto.value = null
    }

}