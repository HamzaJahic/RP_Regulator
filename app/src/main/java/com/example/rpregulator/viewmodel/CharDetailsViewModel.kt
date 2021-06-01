package com.example.rpregulator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpregulator.firebase.CharsFirebase
import com.example.rpregulator.models.Chars
import kotlinx.coroutines.launch

class CharDetailsViewModel(var char: Chars) : ViewModel() {

    val charName = MutableLiveData<String?>()
    val charApperance = MutableLiveData<String?>()
    val charDesc = MutableLiveData<String?>()
    var id = String()
    var img = String()


    init {
        charName.value = char.name
        charApperance.value = char.ark
        charDesc.value = char.desc
        id = char.id!!
        img = char.img!!
    }


    private val _navigateToCharEdit = MutableLiveData<Chars?>()
    val navigateToCharEdit: LiveData<Chars?>
        get() = _navigateToCharEdit

    private val _navigateToChar = MutableLiveData<Boolean?>()
    val navigateToChar: LiveData<Boolean?>
        get() = _navigateToChar

    private val _showAlertDialog = MutableLiveData<Boolean?>()
    val showAlertDialog: LiveData<Boolean?>
        get() = _showAlertDialog

    private val _uploadPhoto = MutableLiveData<Boolean?>()
    val uploadPhoto: LiveData<Boolean?>
        get() = _uploadPhoto


    fun navigateToCharEdit() {
        _navigateToCharEdit.value = char
        doneNavigateToCharEdit()

    }

    private fun doneNavigateToCharEdit() {
        _navigateToCharEdit.value = null
    }

    private fun navigateToChar() {
        _navigateToChar.value = true
        doneNavigateToChar()

    }

    private fun doneNavigateToChar() {
        _navigateToChar.value = null
    }


    fun editData() {
        CharsFirebase.databaseReference
        val entryID = id
        val entry = Chars(
            entryID,
            img,
            charName.value,
            charApperance.value,
            charDesc.value
        )
        viewModelScope.launch {
            CharsFirebase.uploadData(entryID, entry)
            Log.d("Img", img)
            CharsFirebase.databaseReference.child(char.id!!).child("img").setValue(img)
        }
    }

    fun deleteEntry() {
        val entryID = id
        viewModelScope.launch {
            CharsFirebase.databaseReference.child(entryID).removeValue()
            navigateToChar()
        }
    }

    fun showAlertDialog() {
        _showAlertDialog.value = true
        doneShow()
    }

    private fun doneShow() {
        _showAlertDialog.value = null
    }


    fun uploadPhoto() {
        _uploadPhoto.value = true
        doneUploadPhoto()

    }

    private fun doneUploadPhoto() {
        _uploadPhoto.value = null
    }

}