package com.example.play71.interactors

import com.example.play71.*
import com.example.play71.cache.CacheResult
import com.example.play71.domain.BabyName
import com.example.play71.domain.DataState
import com.example.play71.presentation.MyViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertBabyName {

    fun execute(
            name: String,
            stateEvent: StateEvent
    ): Flow<DataState<MyViewState>?> = flow {

        println("MyDebug: Inside insertBabyName with name: $name")
        val babyName = BabyName(name);
        println("MyDebug: Created babyName")

        val cacheResult = CacheResult.Success(1L);
        println("MyDebug: Created cacheResult (Success)")

        val viewState = MyViewState(babyName = BabyName(name))
        val dataState = DataState.data(response = Response(
            message = INSERT_BABYNAME_SUCCESS,
            uiComponentType = UIComponentType.Toast(),
            messageType = MessageType.Success()
            ),
            data = viewState,
            stateEvent = stateEvent)

        emit(dataState)
        /*
        val cacheResponse = object: CacheResponseHandler<MyViewState, Long>(
                response = cacheResult,
                stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: Long): DataState<MyViewState>? {
                return if(resultObj > 0){
                    val viewState =
                            MyViewState(
                                    babyName = babyName
                            )
                    DataState.data(
                            response = Response(
                                    message = INSERT_BABYNAME_SUCCESS,
                                    uiComponentType = UIComponentType.Toast(),
                                    messageType = MessageType.Success()
                            ),
                            data = viewState,
                            stateEvent = stateEvent
                    )
                }
                else{
                    DataState.data(
                            response = Response(
                                    message = INSERT_BABYNAME_FAILED,
                                    uiComponentType = UIComponentType.Toast(),
                                    messageType = MessageType.Error()
                            ),
                            data = null,
                            stateEvent = stateEvent
                    )
                }
            }

        }.getResult()


        emit(cacheResponse)

         */
    }

    companion object{
        val INSERT_BABYNAME_SUCCESS = "Successfully inserted baby name."
        val INSERT_BABYNAME_FAILED = "Failed to insert baby name."
    }
}