package com.example.util

import com.example.domain.util.ErrorEntity
import com.example.domain.util.ErrorHandler
import retrofit2.HttpException
import java.io.IOException

class ErrorHandlerImpl: ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    404 -> ErrorEntity.NotFound
                    401 -> ErrorEntity.AccessDenied
                    503 -> ErrorEntity.ServiceUnavailable
                    408 -> ErrorEntity.RequestTimedOut
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}
