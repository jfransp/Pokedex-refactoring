package com.example.domain.util

sealed class Resource<T> {
    class Success<T>(data: T) : Resource<T>()
    class Error<T>(error: ErrorEntity) : Resource<T>()
}
