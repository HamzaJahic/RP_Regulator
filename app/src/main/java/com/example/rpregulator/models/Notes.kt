package com.example.rpregulator.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Notes(val id: String?,
                 val img: String?,
                 val title: String?,
                 val desc: String?,
): Parcelable {
    constructor(): this ("","","","")
}
