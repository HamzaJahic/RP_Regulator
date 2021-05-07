package com.example.rpregulator.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaperWork(val id: String?,
                     val title: String?,
                     val desc: String?,
): Parcelable {
    constructor(): this ("","","")
}
