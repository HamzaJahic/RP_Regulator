package com.example.rpregulator.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CursesBlessingsHealth(val id: String?,
                                 val name: String?,
                                 val value: String?,
                                 val desc: String?,
): Parcelable {
    constructor(): this ("","","","")
}
