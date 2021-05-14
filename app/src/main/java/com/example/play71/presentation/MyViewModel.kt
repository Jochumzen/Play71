package com.example.play71.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.play71.*
import com.example.play71.domain.BabyName
import com.example.play71.domain.DataState
import com.example.play71.domain.Nationality
import com.example.play71.interactors.GetNationality
import com.example.play71.interactors.InsertBabyName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MyViewModel
@Inject
constructor(
    private val getNationality: GetNationality
) :  BaseViewModel<MyViewState>() {


    private val _nationalitiesMLD: MutableLiveData<List<Nationality>> = MutableLiveData()
    val nationalitiesLD: LiveData<List<Nationality>>
        get() = _nationalitiesMLD

    private fun setNationalitiesMLD(nationalities: List<Nationality>?){
        _nationalitiesMLD.value = nationalities
    }

    override fun setStateEvent(stateEvent: StateEvent) {

        val job: Flow<DataState<MyViewState>?> = when(stateEvent) {

            is MyStateEvent.GetNationalityEvent -> {
                println("MyDebug: Detected GetNationalityEvent")
                getNationality.execute(stateEvent)
            }

            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }

        println("MyDebug: job created, about to launch job: $job")
        launchJob(stateEvent, job)
    }

    override fun handleNewData(data: MyViewState) {
        println("MyDebug: About to handle new data: $data")
        data.let { viewState ->

            viewState.nationalities?.let { nationalities ->
                setNationalitiesMLD(nationalities)
            }
        }

    }

}