package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.NotesFirebase
import com.example.rpregulator.models.Notes
import kotlinx.coroutines.launch

class AddNotesViewModel : ViewModel() {

    val noteTitle = MutableLiveData<String?>()
    val noteDesc = MutableLiveData<String?>()
    var img = String()

    private val _navigateToNotes = MutableLiveData<Boolean?>()
    val navigateToNotes: LiveData<Boolean?>
        get() = _navigateToNotes

    private val _uploadPhoto = MutableLiveData<Boolean?>()
    val uploadPhoto: LiveData<Boolean?>
        get() = _uploadPhoto

    init {
        img = ""
    }

    fun navigateToNotes() {
        _navigateToNotes.value = true
        doneNavigateToNotes()
    }

    private fun doneNavigateToNotes() {
        _navigateToNotes.value = null
    }

    fun uploadPhoto() {
        _uploadPhoto.value = true
        doneUploadPhoto()

    }

    private fun doneUploadPhoto() {
        _uploadPhoto.value = null
    }

    fun uploadData() {
        val databaseReference = NotesFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = Notes(
            entryID,
            img,
            noteTitle.value,
            noteDesc.value
        )
        viewModelScope.launch {
            NotesFirebase.uploadData(entryID, entry)
        }
    }
}