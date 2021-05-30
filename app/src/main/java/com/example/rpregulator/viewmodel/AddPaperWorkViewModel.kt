package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.PaperworkFirebase
import com.example.rpregulator.models.PaperWork
import kotlinx.coroutines.launch

class AddPaperWorkViewModel : ViewModel() {

    val paperWorkTitle = MutableLiveData<String?>()
    val paperWorkDesc = MutableLiveData<String?>()


    private val _navigateToPaperWorks = MutableLiveData<Boolean?>()
    val navigateToPaperWorks: LiveData<Boolean?>
        get() = _navigateToPaperWorks

    private val _uploadPhoto = MutableLiveData<Boolean?>()
    val uploadPhoto: LiveData<Boolean?>
        get() = _uploadPhoto


    fun navigateToPaperWorks() {
        _navigateToPaperWorks.value = true
        doneNavigateToPaperWorks()
    }

    private fun doneNavigateToPaperWorks() {
        _navigateToPaperWorks.value = null
    }

    fun uploadPhoto() {
        _uploadPhoto.value = true
        doneUploadPhoto()

    }

    private fun doneUploadPhoto() {
        _uploadPhoto.value = null
    }


    fun uploadData() {
        val databaseReference = PaperworkFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = PaperWork(
                entryID,
                paperWorkTitle.value,
                paperWorkDesc.value
        )
        viewModelScope.launch {
            PaperworkFirebase.uploadData(entryID, entry)
        }
    }

}