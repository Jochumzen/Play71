package com.example.play71.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BabyName(
    var name: String
) : Parcelable