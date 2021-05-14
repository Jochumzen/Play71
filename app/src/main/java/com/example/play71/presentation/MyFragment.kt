package com.example.play71.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.play71.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyFragment : Fragment(R.layout.fragment_my) {
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myStateEvent : MyStateEvent.GetNationalityEvent =
            MyStateEvent.GetNationalityEvent()

        myViewModel.setStateEvent(myStateEvent)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        myViewModel.nationalitiesLD.observe(viewLifecycleOwner, Observer{ nationalities ->
            if(nationalities != null){
                println("MyDebug: fragment: ${nationalities.size}")
            }
        })
    }

}