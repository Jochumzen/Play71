package com.example.play71

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyViewState (
    var babyName: BabyName? = null
) : Parcelable, ViewState