package com.example.rpregulator.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chars(
    val id: String?,
    val img: String?,
    val name: String?,
    val ark: String?,
    val desc: String?,
) : Parcelable {
    constructor() : this("", "", "", "", "")
}
