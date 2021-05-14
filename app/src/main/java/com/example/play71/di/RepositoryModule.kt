package com.example.play71.di

import com.example.play71.network.NationalityDtoMapper
import com.example.play71.NationalityRepository
import com.example.play71.NationalityRepository_impl
import com.example.play71.network.NationalityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideNationalityRepository(
        nationalityService: NationalityService,
        nationalityDtoMapper: NationalityDtoMapper
    ) : NationalityRepository {
        return NationalityRepository_impl(nationalityService, nationalityDtoMapper)
        //return NationalityRepository_impl(nationalityDtoMapper)
    }
}