package com.example.play71.di

import com.example.play71.network.NationalityDtoMapper
import com.example.play71.network.NationalityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNationalityMapper() : NationalityDtoMapper {
        return NationalityDtoMapper()
    }

    @Singleton
    @Provides
    fun provideNationalityService() : NationalityService {
        return Retrofit.Builder()
            .baseUrl("https://random-data-api.com/")
            //.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))   ??
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NationalityService::class.java)
    }

}