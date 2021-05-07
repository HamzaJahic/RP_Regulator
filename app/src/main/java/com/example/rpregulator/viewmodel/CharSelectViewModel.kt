package com.example.rpregulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rpregulator.models.User

class CharSelectViewModel() : ViewModel() {
  

  private val _navigateToPin = MutableLiveData<User?>()
    val navigateToPin: LiveData<User?>
    get() = _navigateToPin


    fun navigateToPin(user: User){
        _navigateToPin.value = user
        doneNavigateToPin()
      
    }

    fun doneNavigateToPin(){
        _navigateToPin.value = null
    }


}