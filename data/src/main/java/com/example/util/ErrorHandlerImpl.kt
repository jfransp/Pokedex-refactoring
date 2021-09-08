package com.example.util

import com.example.domain.util.ErrorEntity
import com.example.domain.util.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import javax.xml.datatype.DatatypeConstants

class ErrorHandlerImpl: ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.Network
            else -> TODO()
        }
    }

}
