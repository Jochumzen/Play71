package com.example.play71

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BabyName(
    var name: String
) : Parcelable