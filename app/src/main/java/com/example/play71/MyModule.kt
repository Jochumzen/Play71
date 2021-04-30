package com.example.play71

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*
@Module
@InstallIn(ViewModelComponent::class)
object MyModule {

    @Provides
    fun provideString() : String {
        return "Hello"
    }
}

 */

@Module
@InstallIn(ViewModelComponent::class)
object MyModule {

    @Provides
    fun provideInteractors() : MyInteractors {
        return MyInteractors(InsertBabyName())
    }
}