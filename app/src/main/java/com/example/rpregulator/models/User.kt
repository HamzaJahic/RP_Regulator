package com.example.rpregulator.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: String?,
                val username: String?,
                val password: String?,
                val img: String?,
): Parcelable {
    constructor(): this ("","","","")
}
