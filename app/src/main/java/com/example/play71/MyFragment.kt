package com.example.play71

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyFragment : Fragment(R.layout.fragment_my) {
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("MyDebug: ViewModel injected into fragment: $myViewModel")

        val myStateEvent : MyStateEvent.InsertBabyNameEvent =  MyStateEvent.InsertBabyNameEvent("Baby Jesus")
        println("MyDebug: myStateEvent: $myStateEvent")

        println("MyDebug: Calling ViewModel SetStateEvent with the InsertBabyNameEvent")
        myViewModel.setStateEvent(myStateEvent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        myViewModel.viewState.observe(viewLifecycleOwner, Observer{ viewState ->
            if(viewState != null){
                viewState.babyName?.let { babyName ->
                    println("MyDebug: fragment: ${babyName.name}")
                }

            }
        })
    }
}