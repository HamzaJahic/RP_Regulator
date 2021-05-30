package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CharsFirebase
import com.example.rpregulator.models.Chars
import kotlinx.coroutines.launch

class AddCharViewModel : ViewModel() {

    val charName = MutableLiveData<String?>()
    val charArk = MutableLiveData<String?>()
    val charDesc = MutableLiveData<String?>()
    var img = String()


    private val _navigateToChars = MutableLiveData<Boolean?>()
    val navigateToChars: LiveData<Boolean?>
        get() = _navigateToChars

    private val _uploadPhoto = MutableLiveData<Boolean?>()
    val uploadPhoto: LiveData<Boolean?>
        get() = _uploadPhoto

    init {
        img = ""
    }


    fun navigateToChars() {
        _navigateToChars.value = true
        doneNavigateToChars()
    }

    private fun doneNavigateToChars() {
        _navigateToChars.value = null
    }

    fun uploadData() {
        val databaseReference = CharsFirebase.databaseReference
        val entryID = databaseReference.push().key.toString()
        val entry = Chars(
                entryID,
                img,
                charName.value,
                charArk.value,
                charDesc.value
        )
        viewModelScope.launch {
            CharsFirebase.uploadData(entryID, entry)
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