package com.example.domain.util

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: ErrorEntity) : Resource<T>()
}
