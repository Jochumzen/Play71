package com.example.play71.di

import android.app.Application
import androidx.room.Room
import com.example.play71.cache.MyDatabase
import com.example.play71.cache.NationalityDao
import com.example.play71.cache.NationalityEntityMapper
import com.example.play71.presentation.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDB(app: Application) : MyDatabase {
        return Room
            .databaseBuilder(app, MyDatabase::class.java, MyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNationalityDao(db: MyDatabase) : NationalityDao {
        return db.nationalityDao()
    }


    @Singleton
    @Provides
    fun provideNationalityMapper() : NationalityEntityMapper {
        return NationalityEntityMapper()
    }
}