package com.example.rpregulator.utils

import androidx.lifecycle.MutableLiveData

class GlobalConstants {
    companion object {
        val USER_ID = MutableLiveData<String>()
        const val GALLERY_REQUEST_CODE = 123
    }
}