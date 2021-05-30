package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.NotesFirebase
import com.example.rpregulator.models.Notes
import com.firebase.ui.database.FirebaseRecyclerOptions

class NotesViewModel(lifecycleOwner: LifecycleOwner) : ViewModel() {


    private val _navigateToAddNotes = MutableLiveData<Boolean?>()
    val navigateToAddNotes: LiveData<Boolean?>
        get() = _navigateToAddNotes


    private val _navigateToNotesDetails = MutableLiveData<Notes?>()
    val navigateToNotesDetails: LiveData<Notes?>
        get() = _navigateToNotesDetails

    val options = FirebaseRecyclerOptions.Builder<Notes>()
            .setQuery(NotesFirebase.databaseReference, Notes::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()


    fun navigateToAddNotes() {
        _navigateToAddNotes.value = true
        doneNavigateToAddNotes()

    }

    fun doneNavigateToAddNotes() {
        _navigateToAddNotes.value = null
    }

    fun navigateToNotesDetails(notes: Notes) {
        _navigateToNotesDetails.value = notes
        doneNavigateToNotesDetails()

    }

    fun doneNavigateToNotesDetails() {
        _navigateToNotesDetails.value = null
    }

}