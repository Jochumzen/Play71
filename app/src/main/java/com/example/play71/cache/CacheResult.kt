package com.example.play71.cache

sealed class CacheResult<out T> {
    data class Success<out T>(val value: T): CacheResult<T>()

    data class GenericError(
            val errorMessage: String? = null
    ): CacheResult<Nothing>()
}