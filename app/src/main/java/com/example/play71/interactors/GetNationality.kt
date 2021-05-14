package com.example.play71.interactors

import android.util.Log
import com.example.play71.*
import com.example.play71.cache.*
import com.example.play71.domain.BabyName
import com.example.play71.domain.DataState
import com.example.play71.domain.Nationality
import com.example.play71.network.NationalityDto
import com.example.play71.network.NationalityDtoMapper
import com.example.play71.network.NationalityService
import com.example.play71.presentation.MyViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNationality(
    private val nationalityService : NationalityService,
    private val nationalityDtoMapper: NationalityDtoMapper,
    private val nationalityDao: NationalityDao,
    private val nationalityEntityMapper: NationalityEntityMapper
) {
    fun execute(
        stateEvent: StateEvent
    ) : Flow<DataState<MyViewState>?> = flow {

        val networkResult = safeApiCall(Dispatchers.IO) {
            nationalityDtoMapper.mapToDomainModel(nationalityService.nationality())
        }

        println("MyDebug: result: $networkResult")

        if(networkResult is ApiResult.Success) {
            println("MyDebug: networkResult is success")

            val cacheResult = safeCacheCall(Dispatchers.IO){
                nationalityDao.insertNationality(networkResult.value?.let {
                    nationalityEntityMapper.mapFromDomainModel(
                        it
                    )
                })
            }
            println("MyDebug: result A: $cacheResult")

            if(cacheResult is CacheResult.Success) {

                val cacheResult = safeCacheCall(Dispatchers.IO){
                    nationalityDao.getAllNationalities()?.let {
                        nationalityEntityMapper.fromEntityList(
                            it
                        )
                    }
                }
                println("MyDebug: result B: $cacheResult")

                val cacheResponse = object: CacheResponseHandler<MyViewState, List<Nationality>>(
                    response = cacheResult,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<Nationality>): DataState<MyViewState>? {
                        val viewState = MyViewState(nationalities = resultObj)

                        Log.d("MyDebug", "insertNewNote $viewState")
                        return DataState.data(
                            response = Response(
                                message = "Getting nationality from network success",
                                uiComponentType = UIComponentType.Toast(),
                                messageType = MessageType.Success()
                            ),
                            data = viewState,
                            stateEvent = stateEvent
                        )
                    }
                }.getResult()

                emit(cacheResponse)


            } else {
                val dataState = DataState.error<MyViewState>(
                    Response(
                        message = "Failed to insert new nationality into db",
                        uiComponentType = UIComponentType.Toast(),
                        messageType = MessageType.Error()
                    ), stateEvent)
                emit(dataState)
            }

        } else {
            val dataState = DataState.error<MyViewState>(
                Response(
                    message = "Getting nationality from network failed",
                    uiComponentType = UIComponentType.Toast(),
                    messageType = MessageType.Error()
                ), stateEvent)
            emit(dataState)
        }

    }
}