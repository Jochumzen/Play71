package com.example.play71.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NationalityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNationality(nationality: com.example.play71.cache.NationalityEntity?)


    @Query("SELECT * FROM nationalities")
    suspend fun getAllNationalities(): List<NationalityEntity>?

    @Query("DELETE FROM nationalities")
    suspend fun deleteAllNationalities()



}