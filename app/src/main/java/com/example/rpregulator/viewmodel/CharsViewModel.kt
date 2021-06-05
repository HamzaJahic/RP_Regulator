package com.example.rpregulator.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.firebase.CharsFirebase
import com.example.rpregulator.models.Chars
import com.firebase.ui.database.FirebaseRecyclerOptions

class CharsViewModel(lifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _navigateToAddChars = MutableLiveData<Boolean?>()
    val navigateToAddChars: LiveData<Boolean?>
        get() = _navigateToAddChars

    private val _navigateToCharsDetails = MutableLiveData<Chars?>()
    val navigateToCharsDetails: LiveData<Chars?>
        get() = _navigateToCharsDetails

    val options = FirebaseRecyclerOptions.Builder<Chars>()
        .setQuery(CharsFirebase.databaseReference.orderByChild("name"), Chars::class.java)
        .setLifecycleOwner(lifecycleOwner)
        .build()

    fun navigateToCharsDetails(chars: Chars) {
        _navigateToCharsDetails.value = chars
        doneNavigateToCharsDetails()

    }

    private fun doneNavigateToCharsDetails() {
        _navigateToCharsDetails.value = null
    }

    fun navigateToAddChars() {
        _navigateToAddChars.value = true
        doneNavigateToAddChars()

    }

    private fun doneNavigateToAddChars() {
        _navigateToAddChars.value = null
    }


}