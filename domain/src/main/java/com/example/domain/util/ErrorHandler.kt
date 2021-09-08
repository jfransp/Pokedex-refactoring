package com.example.domain.util

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}