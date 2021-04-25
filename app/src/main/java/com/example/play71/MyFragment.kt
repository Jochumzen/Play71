package com.example.play71

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class MyFragment : Fragment(R.layout.fragment_my) {
    val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("MyFrag: $myViewModel")
    }
}