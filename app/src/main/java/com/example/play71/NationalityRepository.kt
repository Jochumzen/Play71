package com.example.play71

import com.example.play71.domain.Nationality

interface NationalityRepository {
    suspend fun getNationality(): Nationality
}