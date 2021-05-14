package com.example.play71.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NationalityEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun nationalityDao() : NationalityDao

    companion object{
        val DATABASE_NAME = "nationality_db"
    }
}