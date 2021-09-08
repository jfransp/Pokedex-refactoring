package com.example.util

import com.example.domain.util.ErrorEntity
import com.example.domain.util.ErrorHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException
import javax.xml.datatype.DatatypeConstants

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
