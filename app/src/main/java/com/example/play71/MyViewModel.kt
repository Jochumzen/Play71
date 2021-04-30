package com.example.play71

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MyViewModel
@Inject
constructor(
    private val myInteractors: MyInteractors
) : BaseViewModel<MyViewState>() {


    override fun setStateEvent(stateEvent: StateEvent) {

        println("MyDebug: Inside setStateEvent. MyInteractors are injected into ViewModel: $myInteractors")

        val job: Flow<DataState<MyViewState>?> = when(stateEvent) {

            is MyStateEvent.InsertBabyNameEvent -> {
                println("MyDebug: Detected InsertBabyNameEvent")
                myInteractors.insertBabyName.insertBabyName(stateEvent.name, stateEvent) //returns Flow<DataState<MyViewState>?>

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

            viewState.babyName?.let { babyName ->
                setBabyName(babyName)
            }
        }

    }

    fun setBabyName(babyName: BabyName?){
        val update = getCurrentViewStateOrNew()
        update.babyName = babyName
        setViewState(update)
    }

    override fun initNewViewState(): MyViewState {
        return MyViewState()
    }

}