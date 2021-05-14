package com.example.play71.di

import android.content.Context
import com.example.play71.NationalityRepository
import com.example.play71.cache.NationalityDao
import com.example.play71.cache.NationalityEntityMapper
import com.example.play71.interactors.GetNationality
import com.example.play71.interactors.InsertBabyName
import com.example.play71.network.NationalityDto
import com.example.play71.network.NationalityDtoMapper
import com.example.play71.network.NationalityService
import com.example.play71.presentation.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object MyModule {

    @Singleton
    @Provides
    fun provideApplication(@ViewModelScoped app: Context): MyApplication {
        return app as MyApplication
    }

    @Provides
    fun provideInsertBabyName(
    ) : InsertBabyName {
        return InsertBabyName()
    }

    @Provides
    fun getNationality(
        nationalityService: NationalityService,
        nationalityDtoMapper: NationalityDtoMapper,
        nationalityDao: NationalityDao,
        nationalityEntityMapper: NationalityEntityMapper
    ) : GetNationality {
        return GetNationality(nationalityService, nationalityDtoMapper, nationalityDao, nationalityEntityMapper)
    }
}
