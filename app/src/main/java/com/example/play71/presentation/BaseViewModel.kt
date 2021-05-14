package com.example.play71.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.play71.*
import com.example.play71.domain.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel<ViewState> : ViewModel() {

    //private val _viewState: MutableLiveData<ViewState> = MutableLiveData()

    val dataChannelManager: DataChannelManager<ViewState>
            = object: DataChannelManager<ViewState>(){

        override fun handleNewData(data: ViewState) {
            this@BaseViewModel.handleNewData(data)
        }
    }

    abstract fun handleNewData(data: ViewState)

    //val viewState: LiveData<ViewState>
        //get() = _viewState

    abstract fun setStateEvent(stateEvent: StateEvent)

    /*
    fun setViewState(viewState: ViewState){
        _viewState.value = viewState
    }

    fun getCurrentViewStateOrNew(): ViewState {
        return viewState.value ?: initNewViewState()
    }

    abstract fun initNewViewState(): ViewState

     */

    fun emitInvalidStateEvent(stateEvent: StateEvent) = flow {
        emit(
                DataState.error<ViewState>(
                        response = Response(
                                message = GenericErrors.INVALID_STATE_EVENT,
                                uiComponentType = UIComponentType.None(),
                                messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                )
        )
    }

    fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataState<ViewState>?>
    ) = dataChannelManager.launchJob(stateEvent, jobFunction)
}