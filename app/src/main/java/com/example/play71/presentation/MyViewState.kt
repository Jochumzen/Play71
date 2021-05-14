package com.example.play71.presentation

import android.os.Parcelable
import com.example.play71.domain.BabyName
import com.example.play71.domain.Nationality
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyViewState (
    var nationalities: List<Nationality>? = null,
) : Parcelable, ViewState