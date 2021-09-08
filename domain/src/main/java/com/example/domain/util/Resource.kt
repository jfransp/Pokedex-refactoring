package com.example.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val error: ErrorEntity? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorEntity, data: T? = null) : Resource<T>(data, error)
}
