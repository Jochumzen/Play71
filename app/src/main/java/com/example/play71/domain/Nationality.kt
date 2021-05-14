package com.example.play71.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nationality (
    val id: Int? = null,
    val uid: String? = null,
    val nationality: String? = null,
    val language: String? = null,
    val capital: String? = null,
    val national_sport: String? = null,
    val flag: String? = null
) :Parcelable {

}