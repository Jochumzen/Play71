package com.example.play71.cache

import com.example.play71.MessageType
import com.example.play71.Response
import com.example.play71.StateEvent
import com.example.play71.UIComponentType
import com.example.play71.cache.CacheErrors.CACHE_DATA_NULL
import com.example.play71.domain.DataState

abstract class CacheResponseHandler <ViewState, Data>(
    private val response: CacheResult<Data?>,
    private val stateEvent: StateEvent?
) {
    fun getResult(): DataState<ViewState>? {

        return when(response){

            is CacheResult.GenericError -> {
                DataState.error(
                        response = Response(
                                message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage}",
                                uiComponentType = UIComponentType.Dialog(),
                                messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                )
            }

            is CacheResult.Success -> {
                if(response.value == null){
                    DataState.error(
                            response = Response(
                                    message = "${stateEvent?.errorInfo()}\n\nReason: ${CACHE_DATA_NULL}.",
                                    uiComponentType = UIComponentType.Dialog(),
                                    messageType = MessageType.Error()
                            ),
                            stateEvent = stateEvent
                    )
                }
                else{
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract fun handleSuccess(resultObj: Data): DataState<ViewState>?
}