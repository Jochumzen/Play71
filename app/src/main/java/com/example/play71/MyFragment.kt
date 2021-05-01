package com.example.play71

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.play71.databinding.ActivityMainBinding
import com.example.play71.databinding.FragmentMyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize


@AndroidEntryPoint
class MyFragment : Fragment(R.layout.fragment_my) {
    private val myViewModel: MyViewModel by viewModels()

    private lateinit var binding: FragmentMyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("MyDebug: ViewModel injected into fragment: $myViewModel")
        binding = FragmentMyBinding.inflate(layoutInflater)

        return binding.root
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("MyDebug: ViewModel injected into fragment: $myViewModel")

        binding = FragmentMyBinding.inflate(LayoutInflater.from(context))

        val myStateEvent : MyStateEvent.InsertBabyNameEvent =  MyStateEvent.InsertBabyNameEvent("Baby Jesus")
        println("MyDebug: myStateEvent: $myStateEvent")

        binding.button.setOnClickListener {
            println("MyDebug: Calling ViewModel SetStateEvent with the InsertBabyNameEvent")
            myViewModel.setStateEvent(myStateEvent)
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myStateEvent : MyStateEvent.InsertBabyNameEvent =  MyStateEvent.InsertBabyNameEvent("Baby Jesus")
        println("MyDebug: myStateEvent: $myStateEvent")

        binding.button.setOnClickListener {
            println("MyDebug: Calling ViewModel SetStateEvent with the InsertBabyNameEvent")
            myViewModel.setStateEvent(myStateEvent)
        }

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