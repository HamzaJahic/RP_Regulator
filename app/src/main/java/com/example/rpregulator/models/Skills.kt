package com.example.rpregulator.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skills(val id: String?,
                  val name: String?,
                  val type: String?,
                  val cost: String?,
                  val desc: String?,
                  val value: String?
) : Parcelable {
    constructor() : this("", "", "", "", "", "")
}
